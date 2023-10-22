package pl.fitnote.activity;

import pl.fitnote.commons.UserDetails;

import java.util.List;

public interface ActivityTypeFacade {
    Long createActivityType(ActivityTypeDto command, UserDetails userDetails);

    void updateActivityType(Long activityTypeId, ActivityTypeDto command, UserDetails userDetails);

    List<ActivityTypeProjection> getAllActivityTypes(UserDetails userDetails);

    <T> T getActivityType(Long activityTypeId, UserDetails userDetails, Class<T> type);

    void deleteActivityType(Long activityTypeId, UserDetails userDetails);
}
