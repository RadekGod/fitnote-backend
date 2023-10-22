package pl.fitnote.trainingPlan;

public interface ExerciseSetProjection {
    Long getId();
    Float getWeight();
    Long getRepeats();
    Boolean getCompleted();
    String getNote();
}
