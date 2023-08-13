package pl.fitnote.exerciseSet;


import pl.fitnote.commons.UserDetails;
import pl.fitnote.trainingPlan.TrainingPlan;

import java.util.List;

public interface ExerciseSetFacade {
    void addExerciseToTrainingPlan(TrainingPlan trainingPlan, List<ExerciseSetDto> command, UserDetails userDetails);
    void updateExerciseInTrainingPlan(TrainingPlan trainingPlan, List<ExerciseSetDto> command, UserDetails userDetails);
    void deleteExerciseFromTrainingPlan(TrainingPlan trainingPlan, UserDetails userDetails);
}
