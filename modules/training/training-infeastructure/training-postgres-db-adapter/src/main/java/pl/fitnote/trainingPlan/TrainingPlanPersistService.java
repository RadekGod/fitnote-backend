package pl.fitnote.trainingPlan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fitnote.training.model.TrainingPlan;
import pl.fitnote.training.port.outgoing.TrainingPlanPersistPort;

@Service
@RequiredArgsConstructor
public class TrainingPlanPersistService implements TrainingPlanPersistPort {
    @Override
    public Long save(TrainingPlan trainingPlan) {
        return null;
    }

    @Override
    public void update(TrainingPlan trainingPlan) {

    }
}
