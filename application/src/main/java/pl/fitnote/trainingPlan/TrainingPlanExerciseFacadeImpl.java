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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class TrainingPlanExerciseFacadeImpl implements TrainingPlanExerciseFacade {

    private final TrainingPlanExerciseFactory trainingPlanExerciseFactory;
    private final ExerciseSetFactory exerciseSetFactory;
    private final TrainingPlanFacade trainingPlanFacade;
    private final ExerciseFacade exerciseFacade;
    private final ExerciseSetPersistRepository exerciseSetPersistRepository;
    private final TrainingPlanExercisePersistRepository trainingPlanExercisePersistRepository;
    private final TrainingPlanExerciseQueryRepository trainingPlanExerciseQueryRepository;

    @Override
    @Transactional
    public SimpleTrainingPlanExerciseProjection getTrainingPlanExercise(final Long trainingPlanId, final Long trainingPlanExerciseId, final UserDetails userDetails) {
        return trainingPlanExerciseQueryRepository.findByIdAndTrainingPlanIdAndTrainingPlanUserEmailOrderByIdAsc(trainingPlanExerciseId, trainingPlanId, userDetails.getEmail())
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public <T> List<T> getAllExercisesFromTrainingPlan(final Long trainingPlanId, final UserDetails userDetails, Class<T> type) {
        return trainingPlanExerciseQueryRepository.findAllTrainingPlanExercisesOfGivenTrainingPlan(trainingPlanId, userDetails.getEmail(), type);
    }

    @Override
    @Transactional
    public void addExerciseToTrainingPlan(Long trainingPlanId, Long exerciseId, final TrainingPlanExerciseDto command, final UserDetails userDetails) {
        TrainingPlan trainingPlan = trainingPlanFacade.getTrainingPlan(trainingPlanId, userDetails, TrainingPlan.class);
        Exercise exercise = exerciseFacade.getExercise(exerciseId, userDetails, Exercise.class);
        TrainingPlanExercise trainingPlanExercise = trainingPlanExerciseFactory.createTrainingPlanExercise(command, exercise, trainingPlan);
        TrainingPlanExercise savedTrainingPlanExercise = trainingPlanExercisePersistRepository.save(trainingPlanExercise);

        List<ExerciseSet> exerciseSetList = exerciseSetFactory.createExerciseSet(command.getExerciseSets(), savedTrainingPlanExercise);
        exerciseSetPersistRepository.saveAll(exerciseSetList);
    }

    @Override
    @Transactional
    public void updateTrainingPlanExercise(final Long trainingPlanId, Long trainingPlanExerciseId, final TrainingPlanExerciseDto command, final UserDetails userDetails) {
        TrainingPlanExercise trainingPlanExerciseToUpdate = trainingPlanExerciseQueryRepository.findByIdAndTrainingPlanIdAndTrainingPlanUserEmail(trainingPlanExerciseId, trainingPlanId, userDetails.getEmail())
                .orElseThrow(EntityNotFoundException::new);
        List<ExerciseSet> exerciseSetsBeforeUpdate = trainingPlanExerciseQueryRepository.findByIdAndTrainingPlanIdAndTrainingPlanUserEmail(trainingPlanExerciseId, trainingPlanId, userDetails.getEmail())
                .orElseThrow(EntityNotFoundException::new).getExerciseSets();
        List<ExerciseSet> exerciseSetsToSave = new ArrayList<>();

        trainingPlanExerciseToUpdate.setNote(command.getNote());

        if (command.getExerciseSets().size() >= exerciseSetsBeforeUpdate.size()) {
            for (int i = 0; i < command.getExerciseSets().size(); i++) {
                if (i < exerciseSetsBeforeUpdate.size()) {
                    exerciseSetsToSave.add(trainingPlanExerciseFactory.updateExerciseSet(exerciseSetsBeforeUpdate.get(i), command.getExerciseSets().get(i)));
                } else {
                    exerciseSetsToSave.add(trainingPlanExerciseFactory.createExerciseSet(command.getExerciseSets().get(i), trainingPlanExerciseToUpdate));
                }
            }
        } else {
            for (int i = 0; i < command.getExerciseSets().size(); i++) {
                exerciseSetsToSave.add(trainingPlanExerciseFactory.updateExerciseSet(exerciseSetsBeforeUpdate.get(i), command.getExerciseSets().get(i)));
            }
            List<ExerciseSet> exerciseSetsToDelete = exerciseSetsBeforeUpdate.subList(command.getExerciseSets().size(), exerciseSetsBeforeUpdate.size());
            exerciseSetPersistRepository.deleteExerciseSetsByIds(exerciseSetsToDelete.stream().map(ExerciseSet::getId).collect(Collectors.toList()));
            exerciseSetPersistRepository.flush();
        }
        exerciseSetPersistRepository.saveAll(exerciseSetsToSave);

        trainingPlanExercisePersistRepository.saveAndFlush(trainingPlanExerciseToUpdate);
    }

    @Override
    @Transactional
    public void deleteExerciseFromTrainingPlan(final Long trainingPlanId, final Long trainingPlanExerciseId, final UserDetails userDetails) {
        TrainingPlanExercise toDelete = trainingPlanExerciseQueryRepository.findById(trainingPlanExerciseId)
                .orElseThrow(EntityNotFoundException::new);

        exerciseSetPersistRepository.deleteExerciseSetsByIds(toDelete.getExerciseSets().stream().map(ExerciseSet::getId).collect(Collectors.toList()));
        trainingPlanExercisePersistRepository.deleteTrainingPlanExerciseById(toDelete.getId());
    }
}
