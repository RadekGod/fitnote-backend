package pl.fitnote.trainingPlan;

import org.springframework.stereotype.Service;
import pl.fitnote.user.User;

@Service
public class TrainingPlanFactory {

    public TrainingPlan createTrainingPlanFromDto(CreateTrainingPlanDto source, User requestingUser) {
        return TrainingPlan.builder()
                .name(source.getName())
                .trainingDays(source.getTrainingDays())
                .note(source.getNote())
//                .exerciseSets(source.getExerciseSets())
                .user(requestingUser)
                .build();
    }
}
