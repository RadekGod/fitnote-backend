package pl.fitnote.exerciseSet;

import pl.fitnote.exercise.dto.ExerciseProjection;

public interface ExerciseSetProjection {
    Long getId();
    Float getWeight();
    MeasureUnit getMeasureUnit();
    Long getRepeats();
    Boolean getCompleted();
    String getNote();
    ExerciseProjection getExercise();
//    TrainingPlanProjection getTrainingPlan();
}
