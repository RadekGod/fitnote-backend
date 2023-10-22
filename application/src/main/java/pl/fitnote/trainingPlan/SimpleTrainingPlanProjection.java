package pl.fitnote.trainingPlan;

import java.util.List;

public interface SimpleTrainingPlanProjection {
    Long getId();
    String getName();
    List<TrainingDay> getTrainingDays();
    List<SimpleTrainingPlanExerciseProjection> getTrainingPlanExercises();
}
