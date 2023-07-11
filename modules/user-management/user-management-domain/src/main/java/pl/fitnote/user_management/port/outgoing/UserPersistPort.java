package pl.fitnote.user_management.port.outgoing;

import pl.fitnote.user_management.model.user.User;

public interface UserPersistPort {

    Long saveUser(User user);
}
