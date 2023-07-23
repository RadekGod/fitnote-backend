package pl.fitnote.trainingPlan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fitnote.commons.UserDetails;

@Service
@RequiredArgsConstructor
public class TrainingPlanFacadeImpl implements TrainingPlanFacade {

    private final TrainingPlanFactory trainingPlanFactory;
    @Override
    public Long createTrainingPlan(final TrainingPlanDto command, final UserDetails userDetails) {

        return null;
    }
}
