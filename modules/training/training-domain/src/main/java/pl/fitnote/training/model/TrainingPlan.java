package pl.fitnote.training.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.fitnote.training.model.vo.TrainingPlanDay;
import pl.fitnote.training.model.vo.TrainingPlanId;
import pl.fitnote.training.model.vo.TrainingPlanName;
import pl.fitnote.training.model.vo.TrainingPlanNote;

import java.util.List;

@Getter
@Setter
@Builder
public class TrainingPlan {
    private TrainingPlanId id;
    private TrainingPlanName trainingPlanName;
    private List<TrainingPlanExercise> exercises;
    private List<TrainingPlanDay> trainingPlanDays;
    private TrainingPlanNote note;

}
