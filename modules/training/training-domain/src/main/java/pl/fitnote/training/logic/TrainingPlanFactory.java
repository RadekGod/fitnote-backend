package pl.fitnote.training.logic;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.fitnote.training.command.NewTrainingPlanCommand;
import pl.fitnote.training.model.TrainingPlan;
import pl.fitnote.training.model.vo.TrainingPlanName;
import pl.fitnote.training.model.vo.TrainingPlanNote;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TrainingPlanFactory {

    public static TrainingPlan buildTrainingPlan(NewTrainingPlanCommand command) {
        return TrainingPlan.builder()
                .trainingPlanName(new TrainingPlanName(command.trainingPlanName()))
                .exercises(command.exercises())
                .trainingPlanDays(command.trainingPlanDays())
                .note(new TrainingPlanNote(command.note()))
                .build();
    }
}
