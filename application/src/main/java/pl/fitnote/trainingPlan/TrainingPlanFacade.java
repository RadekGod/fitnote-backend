package pl.fitnote.trainingPlan;

import pl.fitnote.commons.UserDetails;

import java.util.List;

public interface TrainingPlanFacade {

    Long createTrainingPlan(CreateTrainingPlanDto command, UserDetails userDetails);
    void updateTrainingPlan(Long trainingPlanId, TrainingPlanDto command, UserDetails userDetails);
    <T> T getTrainingPlan(Long trainingPlanId, UserDetails userDetails, Class<T> type);
    List<TrainingPlanProjection> getAllTrainingPlans(UserDetails userDetails);
    void deleteTrainingPlan(final Long trainingPlanId, final UserDetails userDetails);

}
