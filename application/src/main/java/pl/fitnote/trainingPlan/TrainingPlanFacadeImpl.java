package pl.fitnote.trainingPlan;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.exercise.ExerciseFacade;
import pl.fitnote.exerciseSet.ExerciseSetDto;
import pl.fitnote.exerciseSet.ExerciseSetFacade;
import pl.fitnote.user.User;
import pl.fitnote.user.UserFacade;
import pl.fitnote.user.dto.UserProjection;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
class TrainingPlanFacadeImpl implements TrainingPlanFacade {

    private final TrainingPlanFactory trainingPlanFactory;
    private final ExerciseSetFacade exerciseSetFacade;
    private final UserFacade userFacade;
    private final ExerciseFacade exerciseFacade;
    private final TrainingPlanQueryRepository trainingPlanQueryRepository;
    private final TrainingPlanPersistRepository trainingPlanPersistRepository;
    @Override
    public Long createTrainingPlan(final CreateTrainingPlanDto command, final UserDetails userDetails) {
        User requestingUser = userFacade.getUser(userDetails, User.class);
        TrainingPlan toCreate = trainingPlanFactory.createTrainingPlanFromDto(command, requestingUser);
        return trainingPlanPersistRepository.save(toCreate).getId();
    }

    @Override
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
    public void deleteTrainingPlan(final Long trainingPlanId, final UserDetails userDetails) {
        UserProjection requestingUser = userFacade.getUser(userDetails, UserProjection.class);
        TrainingPlanProjection toDelete = trainingPlanQueryRepository.findTrainingPlanForUserByKeycloakId(trainingPlanId, userDetails.getKeycloakId(), TrainingPlanProjection.class)
                .orElseThrow(EntityNotFoundException::new);

        if (isOwnerOfTrainingPlan(requestingUser, toDelete)) {
            trainingPlanPersistRepository.deleteById(toDelete.getId());
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    private boolean isOwnerOfTrainingPlan(UserProjection requestingUser, TrainingPlanProjection trainingPlan) {
        return Objects.equals(requestingUser.getId(), trainingPlan.getId());
    }


    @Override
    public void addExerciseToTrainingPlan(final Long trainingPlanId, final List<ExerciseSetDto> command, final UserDetails userDetails) {
        TrainingPlan toUpdate = trainingPlanQueryRepository.findTrainingPlanForUserByKeycloakId(trainingPlanId, userDetails.getKeycloakId(), TrainingPlan.class)
                .orElseThrow(EntityNotFoundException::new);
        exerciseSetFacade.addExerciseToTrainingPlan(toUpdate, command, userDetails);
        trainingPlanPersistRepository.save(toUpdate);
    }

    @Override
    public void updateExerciseInTrainingPlan(final Long trainingPlanId, final List<ExerciseSetDto> command, final UserDetails userDetails) {
        TrainingPlan toUpdate = trainingPlanQueryRepository.findTrainingPlanForUserByKeycloakId(trainingPlanId, userDetails.getKeycloakId(), TrainingPlan.class)
                .orElseThrow(EntityNotFoundException::new);
        exerciseSetFacade.updateExerciseInTrainingPlan(toUpdate, command, userDetails);
        trainingPlanPersistRepository.save(toUpdate);
    }

    @Override
    public void deleteExerciseFromTrainingPlan(final Long trainingPlanId, final UserDetails userDetails) {
        TrainingPlan toUpdate = trainingPlanQueryRepository.findTrainingPlanForUserByKeycloakId(trainingPlanId, userDetails.getKeycloakId(), TrainingPlan.class)
                .orElseThrow(EntityNotFoundException::new);
        exerciseSetFacade.deleteExerciseFromTrainingPlan(toUpdate, userDetails);
        trainingPlanPersistRepository.save(toUpdate);
    }

}
