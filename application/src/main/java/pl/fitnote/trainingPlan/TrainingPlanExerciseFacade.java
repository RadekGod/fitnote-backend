package pl.fitnote.trainingPlan;


import pl.fitnote.commons.UserDetails;

import java.util.List;

public interface TrainingPlanExerciseFacade {
    SimpleTrainingPlanExerciseProjection getTrainingPlanExercise(Long trainingPlanId, Long trainingPlanExerciseId, UserDetails userDetails);
    <T> List<T> getAllExercisesFromTrainingPlan(Long trainingPlanId, UserDetails userDetails, Class<T> type);
    void addExerciseToTrainingPlan(Long trainingPlanId, Long exerciseId, TrainingPlanExerciseDto command, UserDetails userDetails);
    void updateTrainingPlanExercise(Long trainingPlanId, Long trainingPlanExerciseId, TrainingPlanExerciseDto command, UserDetails userDetails);
    void deleteExerciseFromTrainingPlan(Long trainingPlanId, Long trainingPlanExerciseId, UserDetails userDetails);
}
