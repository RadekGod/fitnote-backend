package pl.fitnote.training.port.incoming;

import pl.fitnote.training.command.UpdateTrainingPlanCommand;

public interface ExistingTrainingPlanPort {
    void updateTrainingPlan(UpdateTrainingPlanCommand command);


}
