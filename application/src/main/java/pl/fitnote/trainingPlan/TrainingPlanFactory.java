package pl.fitnote.trainingPlan;

import org.springframework.stereotype.Service;
import pl.fitnote.user.User;

@Service
class TrainingPlanFactory {

    TrainingPlan createTrainingPlanFromDto(CreateTrainingPlanDto source, User requestingUser) {
        return TrainingPlan.builder()
                .name(source.getName())
                .trainingDays(source.getTrainingDays())
                .note(source.getNote())
                .user(requestingUser)
                .build();
    }
}
