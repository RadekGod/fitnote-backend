package pl.fitnote;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.fitnote.user_management.CreateUserApplication;
import pl.fitnote.user_management.port.incoming.CreateUserPort;
import pl.fitnote.user_management.port.outgoing.UserPersistPort;

@Configuration
@RequiredArgsConstructor
public class BeansConfiguration {

    @Bean
    CreateUserPort createUserPortBean(UserPersistPort userPersistPort) {
        return new CreateUserApplication(userPersistPort);
    }
}
