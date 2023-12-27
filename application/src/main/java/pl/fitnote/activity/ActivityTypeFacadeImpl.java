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
class ActivityTypeFacadeImpl implements ActivityTypeFacade {

    private final UserFacade userFacade;
    private final ActivityTypeService activityTypeService;

    @Override
    @Transactional
    public Long createActivityType(ActivityTypeDto command, UserDetails userDetails) {
        User requestingUser = userFacade.getUser(userDetails, User.class);
        return activityTypeService.createActivityType(command, requestingUser);
    }

    @Override
    @Transactional
    public void updateActivityType(Long activityTypeId, ActivityTypeDto command, UserDetails userDetails) {
        activityTypeService.updateActivityType(activityTypeId, command, userDetails);
    }

    @Override
    public List<ActivityTypeProjection> getAllActivityTypes(UserDetails userDetails) {
        return activityTypeService.getAllActivityTypes(userDetails);
    }

    @Override
    public <T> T getActivityType(Long activityTypeId, UserDetails userDetails, Class<T> type) {
        return activityTypeService.getActivityType(activityTypeId, userDetails, type);
    }

    @Override
    @Transactional
    public void deleteActivityType(Long activityTypeId, UserDetails userDetails) {
        activityTypeService.deleteActivityType(activityTypeId, userDetails);
    }
}
