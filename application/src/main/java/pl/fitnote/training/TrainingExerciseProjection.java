package pl.fitnote.training;

import pl.fitnote.exercise.Exercise;
import pl.fitnote.trainingPlan.MeasurementUnit;

import java.util.List;

public interface TrainingExerciseProjection {
    Long getId();

    MeasurementUnit getMeasureUnit();

    String getNote();

    Exercise getExercise();

    List<TrainingExerciseSetProjection> getExerciseSets();
}
