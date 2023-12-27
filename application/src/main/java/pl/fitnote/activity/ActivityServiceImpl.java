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
class ActivityServiceImpl implements ActivityService {

    private final ActivityFactory activityFactory;
    private final ActivityPersistRepository activityPersistRepository;
    private final ActivityQueryRepository activityQueryRepository;

    @Override
    @Transactional
    public Long createActivity(ActivityDto command, User requestingUser, ActivityType activityType) {
        Activity activityToSave = activityFactory.createActivityFromDto(command, requestingUser, activityType);
        return activityPersistRepository.save(activityToSave).getId();
    }

    @Override
    @Transactional
    public void updateActivity(Long activityId, ActivityDto command, ActivityType activityType, UserDetails userDetails) {
        Activity activityToUpdate = getActivity(activityId, userDetails, Activity.class);
        activityPersistRepository.save(activityFactory.updateActivityWithDto(activityToUpdate, command, activityType));
    }

    @Override
    @Transactional
    public void calculateBurntKilocaloriesIfNeeded(ActivityType activityType, ActivityTypeDto command) {
        if (!activityType.getAverageCaloriesBurntPerHour().equals(command.getAverageCaloriesBurntPerHour())) {
            Set<Activity> activities = activityType.getActivities().stream()
                    .peek(activity -> activity.setBurntKilocalories(activityFactory.calculateBurntKilocalories(activity.getActivityDurationInMinutes(), command.getAverageCaloriesBurntPerHour())))
                    .collect(Collectors.toSet());
            activityPersistRepository.saveAll(activities);
        }
    }

    @Override
    public List<ActivityProjection> getAllActivities(UserDetails userDetails) {
        return activityQueryRepository.findAllByUserEmailOrderByActivityDateDesc(userDetails.getEmail());
    }

    @Override
    public <T> T getActivity(Long activityId, UserDetails userDetails, Class<T> type) {
        return activityQueryRepository.findByIdAndUserEmail(activityId, userDetails.getEmail(), type)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public void deleteActivity(Long activityId, UserDetails userDetails) {
        Activity activityTypeToDelete = activityQueryRepository.findByIdAndUserEmail(activityId, userDetails.getEmail(), Activity.class)
                .orElseThrow(EntityNotFoundException::new);
        activityPersistRepository.delete(activityTypeToDelete);
    }
}
