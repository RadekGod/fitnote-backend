package pl.fitnote.training;

import pl.fitnote.exercise.Exercise;
import pl.fitnote.trainingPlan.MeasureUnit;

import java.util.List;

public interface TrainingExerciseProjection {
    Long getId();
    MeasureUnit getMeasureUnit();
    String getNote();
    Exercise getExercise();
    List<TrainingExerciseSetProjection> getExerciseSets();
}
