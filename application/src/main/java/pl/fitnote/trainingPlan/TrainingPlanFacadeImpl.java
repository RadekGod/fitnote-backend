package pl.fitnote.trainingPlan;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.User;
import pl.fitnote.user.UserFacade;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
class TrainingPlanFacadeImpl implements TrainingPlanFacade {

    private final TrainingPlanFactory trainingPlanFactory;
    private final UserFacade userFacade;
    private final TrainingPlanQueryRepository trainingPlanQueryRepository;
    private final TrainingPlanPersistRepository trainingPlanPersistRepository;
    private final TrainingPlanExerciseQueryRepository trainingPlanExerciseQueryRepository;

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
        TrainingPlan toUpdate = trainingPlanQueryRepository.findByIdAndUserEmail(trainingPlanId, userDetails.getEmail(), TrainingPlan.class)
                .orElseThrow(EntityNotFoundException::new);
        toUpdate.setName(command.getName());
        toUpdate.setTrainingDays(command.getTrainingDays());
        trainingPlanPersistRepository.save(toUpdate);
    }

    @Override
    @Transactional
    public <T> T getTrainingPlan(final Long trainingPlanId, final UserDetails userDetails, Class<T> type) {
        return trainingPlanQueryRepository.findByIdAndUserEmail(trainingPlanId, userDetails.getEmail(), type)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public TrainingPlanDto getTrainingPlan(Long trainingPlanId, UserDetails userDetails) {
        SimpleTrainingPlanProjection trainingPlanProjection = trainingPlanQueryRepository.findByIdAndUserEmail(trainingPlanId, userDetails.getEmail());
            return trainingPlanFactory.createTrainingPlanDtoFromProjection(trainingPlanProjection);
    }


    @Override
    @Transactional
    public List<TrainingPlanDto> getAllTrainingPlans(final UserDetails userDetails) {
        List<TrainingPlanDto> trainingPlanDtoList = new ArrayList<>();
        trainingPlanQueryRepository.findAllByUserEmailOrderByIdAsc(userDetails.getEmail()).forEach(trainingPlanProjection -> {
            trainingPlanDtoList.add(trainingPlanFactory.createTrainingPlanDtoFromProjection(trainingPlanProjection));
        });
        return trainingPlanDtoList;
    }

    @Override
    @Transactional
    public void deleteTrainingPlan(final Long trainingPlanId, final UserDetails userDetails) {
        TrainingPlan toDelete = trainingPlanQueryRepository.findByIdAndUserEmail(trainingPlanId, userDetails.getEmail(), TrainingPlan.class)
                .orElseThrow(EntityNotFoundException::new);
        trainingPlanPersistRepository.delete(toDelete);
    }
}
