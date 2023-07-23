package pl.fitnote.trainingPlan;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TrainingPlanFactory {

//    public static TrainingPlan buildTrainingPlan(NewTrainingPlanCommand command) {
//        return TrainingPlan.builder()
//                .trainingPlanName(new TrainingPlanName(command.trainingPlanName()))
//                .exercises(command.exercises())
//                .trainingPlanDays(command.trainingPlanDays())
//                .note(new TrainingPlanNote(command.note()))
//                .build();
//    }
}
