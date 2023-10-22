package pl.fitnote.trainingPlan;

import pl.fitnote.exercise.ExerciseProjection;

import java.util.List;

public interface TrainingPlanExerciseProjection {
    Long getId();
    MeasurementUnit getMeasurementUnit();
    String getNote();
    ExerciseProjection getExercise();
    List<ExerciseSetProjection> getExerciseSets();
}
