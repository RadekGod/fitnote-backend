package pl.fitnote.user_management;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fitnote.user_management.model.user.User;
import pl.fitnote.user_management.port.outgoing.UserPersistPort;

@Service
@RequiredArgsConstructor
public class UserManagementPersistService implements UserPersistPort {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public Long saveUser(User user) {
        return userRepository.save(UserFactory.fromDomain(user)).getId();
    }
}
