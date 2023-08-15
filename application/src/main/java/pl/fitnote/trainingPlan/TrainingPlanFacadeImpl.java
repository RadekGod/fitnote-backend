package pl.fitnote.trainingPlan;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.User;
import pl.fitnote.user.UserFacade;

import java.util.List;

@Service
@RequiredArgsConstructor
class TrainingPlanFacadeImpl implements TrainingPlanFacade {

    private final TrainingPlanFactory trainingPlanFactory;
    private final UserFacade userFacade;
    private final TrainingPlanQueryRepository trainingPlanQueryRepository;
    private final TrainingPlanPersistRepository trainingPlanPersistRepository;

    @Override
    @Transactional
    public Long createTrainingPlan(final CreateTrainingPlanDto command, final UserDetails userDetails) {
        User requestingUser = userFacade.getUser(userDetails, User.class);
        TrainingPlan toCreate = trainingPlanFactory.createTrainingPlanFromDto(command, requestingUser);
        return trainingPlanPersistRepository.save(toCreate).getId();
    }

    @Override
    @Transactional
    public void updateTrainingPlan(final Long trainingPlanId, final TrainingPlanDto command, final UserDetails userDetails) {
        TrainingPlan toUpdate = trainingPlanQueryRepository.findTrainingPlanForUserByKeycloakId(trainingPlanId, userDetails.getKeycloakId(), TrainingPlan.class)
                .orElseThrow(EntityNotFoundException::new);
        toUpdate.setName(command.getName());
        toUpdate.setTrainingDays(command.getTrainingDays());
        toUpdate.setNote(command.getNote());
        trainingPlanPersistRepository.save(toUpdate);
    }

    @Override
    public <T> T getTrainingPlan(final Long trainingPlanId, final UserDetails userDetails, Class<T> type) {
        return trainingPlanQueryRepository.findTrainingPlanForUserByKeycloakId(trainingPlanId, userDetails.getKeycloakId(), type)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<TrainingPlanProjection> getAllTrainingPlans(final UserDetails userDetails) {
        return trainingPlanQueryRepository.findAllTrainingPlansForUserByKeycloakId(userDetails.getKeycloakId());
    }

    @Override
    @Transactional
    public void deleteTrainingPlan(final Long trainingPlanId, final UserDetails userDetails) {
        TrainingPlan toDelete = trainingPlanQueryRepository.findTrainingPlanForUserByKeycloakId(trainingPlanId, userDetails.getKeycloakId(), TrainingPlan.class)
                .orElseThrow(EntityNotFoundException::new);
        trainingPlanPersistRepository.delete(toDelete);
    }
}
