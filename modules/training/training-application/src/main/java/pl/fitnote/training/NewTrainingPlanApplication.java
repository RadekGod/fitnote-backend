package pl.fitnote.training;

import lombok.RequiredArgsConstructor;
import pl.fitnote.training.command.NewTrainingPlanCommand;
import pl.fitnote.training.logic.TrainingPlanFactory;
import pl.fitnote.training.model.TrainingPlan;
import pl.fitnote.training.model.vo.TrainingPlanId;
import pl.fitnote.training.port.incoming.NewTrainingPlanPort;
import pl.fitnote.training.port.outgoing.TrainingPlanPersistPort;

@RequiredArgsConstructor
public class NewTrainingPlanApplication implements NewTrainingPlanPort {
    private final TrainingPlanPersistPort trainingPlanPersistPort;

    @Override
    public TrainingPlanId createNewTrainingPlan(NewTrainingPlanCommand command) {
        TrainingPlan trainingPlan = TrainingPlanFactory.buildTrainingPlan(command);
        return new TrainingPlanId(trainingPlanPersistPort.save(trainingPlan));
    }
}
