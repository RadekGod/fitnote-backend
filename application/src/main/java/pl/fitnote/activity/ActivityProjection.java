package pl.fitnote.activity;

import java.time.LocalDateTime;

public interface ActivityProjection {
    Long getId();
    Float getactivityDurationInMinutes();
    Integer getBurntCalories();
    Float getDistanceTraveled();
    LocalDateTime getActivityDate();
    ActivityTypeProjection getActivityType();
}
