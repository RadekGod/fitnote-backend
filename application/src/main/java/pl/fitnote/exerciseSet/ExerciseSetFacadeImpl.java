package pl.fitnote.exerciseSet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.exercise.Exercise;
import pl.fitnote.exercise.ExerciseFacade;
import pl.fitnote.trainingPlan.TrainingPlan;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
class ExerciseSetFacadeImpl implements ExerciseSetFacade {

    private final ExerciseFacade exerciseFacade;
    private final ExerciseSetPersistRepository exerciseSetPersistRepository;
    private final ExerciseSetQueryRepository exerciseSetQueryRepository;
    private final ExerciseSetFactory exerciseSetFactory;

    @Override
    public void addExerciseToTrainingPlan(TrainingPlan trainingPlan, final List<ExerciseSetDto> command, final UserDetails userDetails) {
        Exercise exercise = exerciseFacade.getExercise(command.get(0).getExercise().getId(), userDetails, Exercise.class);
        List<ExerciseSet> exerciseSets = command.stream().map(exerciseSetDto -> exerciseSetFactory.createExerciseSet(exerciseSetDto, exercise, trainingPlan)).toList();
        exerciseSetPersistRepository.saveAll(exerciseSets);
    }

    @Override
    public void updateExerciseInTrainingPlan(final TrainingPlan trainingPlan, final List<ExerciseSetDto> command, final UserDetails userDetails) {
        Exercise exercise = exerciseFacade.getExercise(command.get(0).getExercise().getId(), userDetails, Exercise.class);
        List<ExerciseSet> exerciseSetsToUpdate = exerciseSetQueryRepository.findAllExerciseSetsOfGivenExerciseForTrainingId(command.get(0).getExercise().getId(), trainingPlan.getId(), userDetails.getKeycloakId());
        List<ExerciseSet> exerciseSetsToSave = new ArrayList<>();

        if (command.size() >= exerciseSetsToUpdate.size()) {
            for (int i = 0; i < command.size(); i++) {
                if (i < exerciseSetsToUpdate.size()) {
                    exerciseSetsToSave.add(exerciseSetFactory.updateExerciseSet(exerciseSetsToUpdate.get(i), command.get(i), exercise));
                } else {
                    exerciseSetsToSave.add(exerciseSetFactory.createExerciseSet(command.get(i), exercise, trainingPlan));
                }
            }
        } else {
            for (int i = 0; i < command.size(); i++) {
                exerciseSetsToSave.add(exerciseSetFactory.updateExerciseSet(exerciseSetsToUpdate.get(i), command.get(i), exercise));
            }
            List<ExerciseSet> exerciseSetsToDelete = exerciseSetsToUpdate.subList(command.size(), exerciseSetsToUpdate.size());
            exerciseSetPersistRepository.deleteAll(exerciseSetsToDelete);
        }
        exerciseSetPersistRepository.saveAll(exerciseSetsToSave);
    }

    @Override
    public void deleteExerciseFromTrainingPlan(TrainingPlan trainingPlan, final UserDetails userDetails) {

    }
}
