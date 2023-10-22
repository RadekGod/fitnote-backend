package pl.fitnote.trainingPlan;

import pl.fitnote.exercise.SimpleExerciseProjection;

import java.util.List;

public interface SimpleTrainingPlanExerciseProjection {
    Long getId();
    MeasurementUnit getMeasurementUnit();
    String getNote();
    SimpleExerciseProjection getExercise();
    List<ExerciseSetProjection> getExerciseSets();
}
