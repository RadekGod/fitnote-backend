package pl.fitnote.activity;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.User;
import pl.fitnote.user.UserFacade;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class ActivityFacadeImpl implements ActivityFacade {

    private final UserFacade userFacade;
    private final ActivityFactory activityFactory;
    private final ActivityTypeFacade activityTypeFacade;
    private final ActivityPersistRepository activityPersistRepository;
    private final ActivityQueryRepository activityQueryRepository;

    @Override
    @Transactional
    public Long createActivity(ActivityDto command, UserDetails userDetails) {
        User requestingUser = userFacade.getUser(userDetails, User.class);
        ActivityType activityType = activityTypeFacade.getActivityType(command.getActivityType().getId(), userDetails, ActivityType.class);
        Activity activityToSave = activityFactory.createActivityFromDto(command, requestingUser, activityType);
        return activityPersistRepository.save(activityToSave).getId();
    }

    @Override
    @Transactional
    public void updateActivity(Long activityId, ActivityDto command, UserDetails userDetails) {
        Activity activityToUpdate = getActivity(activityId, userDetails, Activity.class);
        ActivityType activityType = activityTypeFacade.getActivityType(command.getActivityType().getId(), userDetails, ActivityType.class);
        activityPersistRepository.save(activityFactory.updateActivityWithDto(activityToUpdate, command, activityType));
    }

    @Override
    @Transactional
    public void calculateBurntCaloriesIfNeeded(Long activityTypeId, ActivityTypeDto command, UserDetails userDetails) {
        ActivityType activityType = activityTypeFacade.getActivityType(activityTypeId, userDetails, ActivityType.class);
        if (!activityType.getAverageCaloriesBurntPerHour().equals(command.getAverageCaloriesBurntPerHour())) {
            Set<Activity> activities = activityType.getActivities().stream()
                    .peek(activity -> activity.setBurntCalories(activityFactory.calculateBurntCalories(activity.getActivityDurationInMinutes(), command.getAverageCaloriesBurntPerHour())))
                    .collect(Collectors.toSet());
            activityPersistRepository.saveAll(activities);
        }
    }

    @Override
    public List<ActivityProjection> getAllActivities(UserDetails userDetails) {
        return activityQueryRepository.findAllActivitiesByGivenKeycloakId(userDetails.getKeycloakId());
    }

    @Override
    public <T> T getActivity(Long activityId, UserDetails userDetails, Class<T> type) {
        return activityQueryRepository.findActivityByGivenIdAndKeycloakId(activityId, userDetails.getKeycloakId(), type)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public void deleteActivity(Long activityId, UserDetails userDetails) {
        Activity activityTypeToDelete = activityQueryRepository.findActivityByGivenIdAndKeycloakId(activityId, userDetails.getKeycloakId(), Activity.class)
                .orElseThrow(EntityNotFoundException::new);
        activityPersistRepository.delete(activityTypeToDelete);
    }
}
