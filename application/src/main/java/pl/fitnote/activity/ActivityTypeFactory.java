package pl.fitnote.activity;

import org.springframework.stereotype.Service;
import pl.fitnote.user.User;

@Service
class ActivityTypeFactory {
    ActivityType createActivityTypeFromDto(ActivityTypeDto source, User user) {
        return ActivityType.builder()
                .name(source.getName())
                .averageCaloriesBurntPerHour(source.getAverageCaloriesBurntPerHour())
                .distanceActivity(source.getDistanceActivity())
                .customActivity(true)
                .user(user)
                .build();
    }

    ActivityType updateActivityTypeWithDto(ActivityType toUpdate, ActivityTypeDto source) {
        return toUpdate.toBuilder()
                .name(source.getName())
                .averageCaloriesBurntPerHour(source.getAverageCaloriesBurntPerHour())
                .distanceActivity(source.getDistanceActivity())
                .build();
    }
}
