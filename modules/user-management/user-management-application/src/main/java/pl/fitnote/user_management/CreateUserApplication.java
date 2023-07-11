package pl.fitnote.user_management;

import lombok.RequiredArgsConstructor;
import pl.fitnote.commons.CreateUserDetails;
import pl.fitnote.user_management.command.CreateUserCommand;
import pl.fitnote.user_management.logic.UserFactory;
import pl.fitnote.user_management.model.user.User;
import pl.fitnote.user_management.model.user.vo.Email;
import pl.fitnote.user_management.model.user.vo.FirstName;
import pl.fitnote.user_management.model.user.vo.KeycloakId;
import pl.fitnote.user_management.model.user.vo.LastName;
import pl.fitnote.user_management.port.incoming.CreateUserPort;
import pl.fitnote.user_management.port.outgoing.UserPersistPort;

@RequiredArgsConstructor
public class CreateUserApplication implements CreateUserPort {

    private final UserPersistPort userPersistPort;

    @Override
    public Long createUser(CreateUserCommand command, CreateUserDetails createUserDetails) {
        User user = UserFactory.createUserDomainObject(command);
        user.setKeycloakId(new KeycloakId(createUserDetails.getKeycloakId()));
        user.setEmail(new Email(createUserDetails.getEmail()));
        user.setFirstName(new FirstName(createUserDetails.getFirstName()));
        user.setLastName(new LastName(createUserDetails.getLastName()));
        return null;
    }
}
