package pl.fitnote.trainingPlan;

import java.util.List;

public interface TrainingPlanProjection {
    Long getId();
    String getName();
    List<TrainingDay> getTrainingDays();
    String getNote();
    List<TrainingPlanExerciseProjection> getTrainingPlanExercises();
}
