package pl.fitnote.trainingPlan;

import pl.fitnote.exercise.ExerciseProjection;

import java.util.List;

public interface TrainingPlanExerciseProjection {
    Long getId();
    MeasureUnit getMeasureUnit();
    String getNote();
    ExerciseProjection getExercise();
    List<ExerciseSetProjection> getExerciseSets();
}
