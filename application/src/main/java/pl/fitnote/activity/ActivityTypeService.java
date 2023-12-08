package pl.fitnote.activity;

import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.User;

import java.util.List;

public interface ActivityTypeService {
    Long createActivityType(ActivityTypeDto command, User requestingUser);

    void updateActivityType(Long activityTypeId, ActivityTypeDto command, UserDetails userDetails);

    List<ActivityTypeProjection> getAllActivityTypes(UserDetails userDetails);

    <T> T getActivityType(Long activityTypeId, UserDetails userDetails, Class<T> type);

    void deleteActivityType(Long activityTypeId, UserDetails userDetails);
}
