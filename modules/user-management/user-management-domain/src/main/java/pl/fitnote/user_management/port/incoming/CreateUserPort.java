package pl.fitnote.user_management.port.incoming;

import pl.fitnote.commons.CreateUserDetails;
import pl.fitnote.user_management.command.CreateUserCommand;

public interface CreateUserPort {
    Long createUser(CreateUserCommand command, CreateUserDetails createUserDetails);
}
