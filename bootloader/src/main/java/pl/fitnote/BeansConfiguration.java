package pl.fitnote;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.fitnote.training.NewTrainingPlanApplication;
import pl.fitnote.training.port.incoming.NewTrainingPlanPort;
import pl.fitnote.training.port.outgoing.TrainingPlanPersistPort;

@Configuration
@RequiredArgsConstructor
public class BeansConfiguration {

//    @Bean
//    CreateUserPort createUserPortBean(UserPersistPort userPersistPort) {
//        return new CreateUserApplication(userPersistPort);
//    }
//
//    @Bean
//    CreateUserPort updateUserPortBean(UserPersistPort userPersistPort, UserReadPort userReadPort) {
//        return new UpdateUserApplication(userPersistPort, userReadPort);
//    }
//
//    @Bean
//    CreateUserPort updateUserSettingsPortBean(UserPersistPort userPersistPort) {
//        return new CreateUserApplication(userPersistPort);
//    }

    @Bean
    NewTrainingPlanPort newTrainingPlanPort(TrainingPlanPersistPort trainingPlanPersistPort) {
        return new NewTrainingPlanApplication(trainingPlanPersistPort);
    }


}
