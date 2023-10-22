package pl.fitnote.trainingPlan;


import pl.fitnote.commons.UserDetails;

import java.util.List;

public interface TrainingPlanExerciseFacade {
    TrainingPlanExerciseProjection getExerciseFromTrainingPlan(Long trainingPlanId, Long trainingPlanExerciseId, UserDetails userDetails);
    <T> List<T> getAllExercisesFromTrainingPlan(Long trainingPlanId, UserDetails userDetails, Class<T> type);
    void addExerciseToTrainingPlan(Long trainingPlanId, TrainingPlanExerciseDto command, UserDetails userDetails);
    void updateExerciseInTrainingPlan(Long trainingPlanId, TrainingPlanExerciseDto command, UserDetails userDetails);
    void deleteExerciseFromTrainingPlan(Long trainingPlanId, Long trainingPlanExerciseId, UserDetails userDetails);
}
