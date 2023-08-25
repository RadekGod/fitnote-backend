package pl.fitnote.activity;

import org.springframework.stereotype.Service;
import pl.fitnote.user.User;

@Service
class ActivityFactory {
    Activity createActivityFromDto(ActivityDto source, User user, ActivityType activityType) {
        return Activity.builder()
                .activityDurationInMinutes(source.getActivityDurationInMinutes())
                .burntCalories(calculateBurntCalories(source.getActivityDurationInMinutes(), activityType.getAverageCaloriesBurntPerHour()))
                .distanceTraveled(source.getDistanceTraveled())
                .activityDate(source.getActivityDate())
                .activityType(activityType)
                .user(user)
                .build();
    }

    Activity updateActivityWithDto(Activity toUpdate, ActivityDto source, ActivityType activityType) {
        return toUpdate.toBuilder()
                .activityDurationInMinutes(source.getActivityDurationInMinutes())
                .burntCalories(calculateBurntCalories(source.getActivityDurationInMinutes(), activityType.getAverageCaloriesBurntPerHour()))
                .distanceTraveled(source.getDistanceTraveled())
                .activityDate(source.getActivityDate())
                .activityType(activityType)
                .build();
    }

    Integer calculateBurntCalories(Float activityDurationInMinutes, Float averageCaloriesBurntPerHour) {
        return Math.round(activityDurationInMinutes * averageCaloriesBurntPerHour / 60);
    }
}
