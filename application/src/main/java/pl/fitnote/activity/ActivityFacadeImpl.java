package pl.fitnote.activity;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.User;
import pl.fitnote.user.UserFacade;

import java.util.List;

@Component
@RequiredArgsConstructor
class ActivityFacadeImpl implements ActivityFacade {

    private final UserFacade userFacade;
    private final ActivityTypeFacade activityTypeFacade;
    private final ActivityService activityService;

    @Override

    public Long createActivity(ActivityDto command, UserDetails userDetails) {
        User requestingUser = userFacade.getUser(userDetails, User.class);
        ActivityType activityType = activityTypeFacade.getActivityType(command.getActivityType().getId(), userDetails, ActivityType.class);
        return activityService.createActivity(command, requestingUser, activityType);
    }

    @Override

    public void updateActivity(Long activityId, ActivityDto command, UserDetails userDetails) {
        ActivityType activityType = activityTypeFacade.getActivityType(command.getActivityType().getId(), userDetails, ActivityType.class);
        activityService.updateActivity(activityId, command, activityType, userDetails);
    }

    @Override

    public void calculateBurntKilocaloriesIfNeeded(Long activityTypeId, ActivityTypeDto command, UserDetails userDetails) {
        ActivityType activityType = activityTypeFacade.getActivityType(activityTypeId, userDetails, ActivityType.class);
        activityService.calculateBurntKilocaloriesIfNeeded(activityType, command);
    }

    @Override
    public List<ActivityProjection> getAllActivities(UserDetails userDetails) {
        return activityService.getAllActivities(userDetails);
    }

    @Override
    public <T> T getActivity(Long activityId, UserDetails userDetails, Class<T> type) {
        return activityService.getActivity(activityId, userDetails, type);
    }

    @Override
    @Transactional
    public void deleteActivity(Long activityId, UserDetails userDetails) {
        activityService.deleteActivity(activityId, userDetails);
    }
}
