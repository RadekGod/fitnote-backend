package pl.fitnote.activity;

import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.User;

import java.util.List;

public interface ActivityService {
    Long createActivity(ActivityDto command, User requestingUser, ActivityType activityType);

    void updateActivity(Long activityId, ActivityDto command, ActivityType activityType, UserDetails userDetails);
    void calculateBurntKilocaloriesIfNeeded(ActivityType activityType, ActivityTypeDto command);

    List<ActivityProjection> getAllActivities(UserDetails userDetails);

    <T> T getActivity(Long activityId, UserDetails userDetails, Class<T> type);

    void deleteActivity(Long activityId, UserDetails userDetails);
}
