package pl.fitnote.activity;

import pl.fitnote.commons.UserDetails;

import java.util.List;

public interface ActivityFacade {
    Long createActivity(ActivityDto command, UserDetails userDetails);

    void updateActivity(Long activityId, ActivityDto command, UserDetails userDetails);
    void calculateBurntKilocaloriesIfNeeded(Long activityTypeId, ActivityTypeDto command, UserDetails userDetails);

    List<ActivityProjection> getAllActivities(UserDetails userDetails);

    <T> T getActivity(Long activityId, UserDetails userDetails, Class<T> type);

    void deleteActivity(Long activityId, UserDetails userDetails);
}
