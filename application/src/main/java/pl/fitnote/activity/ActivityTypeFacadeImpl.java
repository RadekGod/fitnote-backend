package pl.fitnote.activity;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.User;
import pl.fitnote.user.UserFacade;

import java.util.List;

@Service
@RequiredArgsConstructor
class ActivityTypeFacadeImpl implements ActivityTypeFacade {

    private final UserFacade userFacade;
    private final ActivityTypeFactory activityTypeFactory;
    private final ActivityTypePersistRepository activityTypePersistRepository;
    private final ActivityTypeQueryRepository activityTypeQueryRepository;

    @Override
    @Transactional
    public Long createActivityType(ActivityTypeDto command, UserDetails userDetails) {
        User requestingUser = userFacade.getUser(userDetails, User.class);
        ActivityType activityTypeToSave = activityTypeFactory.createActivityTypeFromDto(command, requestingUser);
        return activityTypePersistRepository.save(activityTypeToSave).getId();
    }

    @Override
    @Transactional
    public void updateActivityType(Long activityTypeId, ActivityTypeDto command, UserDetails userDetails) {
        ActivityType activityTypeToUpdate = getActivityType(activityTypeId, userDetails, ActivityType.class);
        activityTypePersistRepository.save(activityTypeFactory.updateActivityTypeWithDto(activityTypeToUpdate, command));
    }

    @Override
    public List<ActivityTypeProjection> getAllActivityTypes(UserDetails userDetails) {
        return activityTypeQueryRepository.findAllByUserEmailOrUserIsNull(userDetails.getEmail());
    }

    @Override
    public <T> T getActivityType(Long activityTypeId, UserDetails userDetails, Class<T> type) {
        return activityTypeQueryRepository.findActivityTypeByGivenIdAndEmail(activityTypeId, type).
                orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public void deleteActivityType(Long activityTypeId, UserDetails userDetails) {
        ActivityType activityTypeToDelete = activityTypeQueryRepository.findCustomActivityTypeByGivenIdAndEmail(activityTypeId, userDetails.getEmail(), ActivityType.class)
                .orElseThrow(EntityNotFoundException::new);
        activityTypePersistRepository.delete(activityTypeToDelete);
    }
}
