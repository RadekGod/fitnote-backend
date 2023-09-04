package pl.fitnote.trainingPlan;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.exercise.Exercise;
import pl.fitnote.exercise.ExerciseFacade;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
class TrainingPlanExerciseFacadeImpl implements TrainingPlanExerciseFacade {

    private final TrainingPlanExerciseFactory trainingPlanExerciseFactory;
    private final ExerciseSetFactory exerciseSetFactory;
    private final TrainingPlanFacade trainingPlanFacade;
    private final ExerciseFacade exerciseFacade;
    private final ExerciseSetPersistRepository exerciseSetPersistRepository;
    private final ExerciseSetQueryRepository exerciseSetQueryRepository;
    private final TrainingPlanExercisePersistRepository trainingPlanExercisePersistRepository;
    private final TrainingPlanExerciseQueryRepository trainingPlanExerciseQueryRepository;

    @Override
    public TrainingPlanExerciseProjection getExerciseFromTrainingPlan(final Long trainingPlanId, final Long trainingPlanExerciseId, final UserDetails userDetails) {
        return trainingPlanExerciseQueryRepository.findTrainingPlanExerciseOfGivenTrainingPlan(trainingPlanExerciseId, userDetails.getEmail())
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<TrainingPlanExerciseProjection> getAllExercisesFromTrainingPlan(final Long trainingPlanId, final UserDetails userDetails) {
        return trainingPlanExerciseQueryRepository.findAllTrainingPlanExercisesOfGivenTrainingPlan(trainingPlanId, userDetails.getEmail());
    }

    @Override
    @Transactional
    public void addExerciseToTrainingPlan(Long trainingPlanId, final TrainingPlanExerciseDto command, final UserDetails userDetails) {
        TrainingPlan trainingPlan = trainingPlanFacade.getTrainingPlan(trainingPlanId, userDetails, TrainingPlan.class);
        Exercise exercise = exerciseFacade.getExercise(command.getExerciseDto().getId(), userDetails, Exercise.class);
        TrainingPlanExercise trainingPlanExercise = trainingPlanExerciseFactory.createTrainingPlanExercise(command, exercise, trainingPlan);
        TrainingPlanExercise savedTrainingPlanExercise = trainingPlanExercisePersistRepository.save(trainingPlanExercise);

        List<ExerciseSet> exerciseSetList = exerciseSetFactory.createExerciseSet(command.getExerciseSetDtoList(), savedTrainingPlanExercise);
        exerciseSetPersistRepository.saveAll(exerciseSetList);
    }

    @Override
    @Transactional
    public void updateExerciseInTrainingPlan(final Long trainingPlanId, final TrainingPlanExerciseDto command, final UserDetails userDetails) {
        List<ExerciseSet> exerciseSetsBeforeUpdate = trainingPlanExerciseQueryRepository.findAllExerciseSetsOfGivenTrainingPlanExerciseId(command.getId(), userDetails.getEmail());
        List<ExerciseSet> exerciseSetsToSave = new ArrayList<>();

        if (command.getExerciseSetDtoList().size() >= exerciseSetsBeforeUpdate.size()) {
            for (int i = 0; i < command.getExerciseSetDtoList().size(); i++) {
                if (i < exerciseSetsBeforeUpdate.size()) {
                    exerciseSetsToSave.add(trainingPlanExerciseFactory.updateExerciseSet(exerciseSetsBeforeUpdate.get(i), command.getExerciseSetDtoList().get(i)));
                } else {
                    TrainingPlanExercise trainingPlanExercise = trainingPlanExerciseQueryRepository.findById(command.getId())
                            .orElseThrow(EntityNotFoundException::new);
                    exerciseSetsToSave.add(trainingPlanExerciseFactory.createExerciseSet(command.getExerciseSetDtoList().get(i), trainingPlanExercise));
                }
            }
        } else {
            for (int i = 0; i < command.getExerciseSetDtoList().size(); i++) {
                exerciseSetsToSave.add(trainingPlanExerciseFactory.updateExerciseSet(exerciseSetsBeforeUpdate.get(i), command.getExerciseSetDtoList().get(i)));
            }
            List<ExerciseSet> exerciseSetsToDelete = exerciseSetsBeforeUpdate.subList(command.getExerciseSetDtoList().size(), exerciseSetsBeforeUpdate.size());
            exerciseSetPersistRepository.deleteAll(exerciseSetsToDelete);
        }
        exerciseSetPersistRepository.saveAll(exerciseSetsToSave);
    }

    @Override
    @Transactional
    public void deleteExerciseFromTrainingPlan(final Long trainingPlanId, final Long trainingPlanExerciseId, final UserDetails userDetails) {
        trainingPlanExercisePersistRepository.deleteById(trainingPlanExerciseId);
    }
}
