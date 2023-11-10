package pl.fitnote.training;

import pl.fitnote.exercise.SimpleExerciseProjection;
import pl.fitnote.trainingPlan.MeasurementUnit;

import java.util.List;

public interface SimpleTrainingExerciseProjection {
    Long getId();

    MeasurementUnit getMeasurementUnit();

    String getNote();

    SimpleExerciseProjection getExercise();

    List<TrainingExerciseSetProjection> getExerciseSets();
}
