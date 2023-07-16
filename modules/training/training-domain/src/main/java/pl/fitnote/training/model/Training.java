package pl.fitnote.training.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.fitnote.training.model.vo.TrainingId;

@Getter
@Setter
@Builder
public class Training {
    private TrainingId id;
    private TrainingPlan trainingPlan;
}
