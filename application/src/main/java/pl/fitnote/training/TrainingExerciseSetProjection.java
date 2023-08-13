package pl.fitnote.training;

public interface TrainingExerciseSetProjection {
    Long getId();
    Float getWeight();
    Long getRepeats();
    Boolean getCompleted();
    String getNote();
}
