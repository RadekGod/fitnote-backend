package pl.fitnote.training.port.incoming;

import pl.fitnote.training.command.NewTrainingPlanCommand;
import pl.fitnote.training.model.vo.TrainingPlanId;

public interface NewTrainingPlanPort {
    TrainingPlanId createNewTrainingPlan(NewTrainingPlanCommand command);
}
