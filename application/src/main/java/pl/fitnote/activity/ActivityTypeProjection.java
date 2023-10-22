package pl.fitnote.activity;

public interface ActivityTypeProjection {
    Long getId();
    String getName();
    Float getAverageCaloriesBurntPerHour();
    Boolean getDistanceActivity();
}
