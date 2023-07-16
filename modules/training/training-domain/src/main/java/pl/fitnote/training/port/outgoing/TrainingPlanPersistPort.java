package pl.fitnote.training.port.outgoing;

import pl.fitnote.training.model.TrainingPlan;

public interface TrainingPlanPersistPort {
    Long save(TrainingPlan trainingPlan);

    void update(TrainingPlan trainingPlan);
}
