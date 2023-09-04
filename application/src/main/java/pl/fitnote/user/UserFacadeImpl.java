package pl.fitnote.user;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.dto.CreateUserDto;
import pl.fitnote.user.dto.UpdateUserDto;
import pl.fitnote.user.dto.UserSettingsDto;

import java.util.Set;

@Service
@RequiredArgsConstructor
class UserFacadeImpl implements UserFacade {

    private final UserPersistRepository userPersistRepository;
    private final UserQueryRepository userQueryRepository;
    private final UserSettingsPersistRepository userSettingsPersistRepository;
    private final AuthorityQueryRepository authorityQueryRepository;
    private final UserFactory userFactory;

    @Override
    @Transactional
    public Long createUser(CreateUserDto command) {
        if (userQueryRepository.existsByEmail(command.getEmail())) {
            throw new EntityExistsException("User already exists");
        } else {
            User userToSave = userFactory.createUserFromDto(command);
            userToSave.setUserAuthorities(getDefaultAuthoritiesForNewUser());
            return userPersistRepository.save(userToSave).getId();
        }
    }

    @Override
    public <T> T getUser(final UserDetails userDetails, final Class<T> type) {
        return userQueryRepository.findUserByEmail(userDetails.getEmail(), type)
                .orElseThrow(() -> new EntityNotFoundException("User not found with given E-mail"));
    }

    @Override
    public <T> T getUser(final String email, final Class<T> type) {
        return userQueryRepository.findUserByEmail(email, type)
                .orElseThrow(() -> new EntityNotFoundException("User not found with given E-mail"));
    }

    @Override
    @Transactional
    public void updateUser(UpdateUserDto command, UserDetails userDetails) {
        User userToUpdate = getUser(userDetails, User.class);
        userToUpdate.setFirstName(command.getFirstName());
        userToUpdate.setLastName(command.getLastName());
        userToUpdate.setBirthDate(command.getBirthDate());
        userToUpdate.setGender(command.getGender());
        userPersistRepository.save(userToUpdate);
    }

    @Override
    @Transactional
    public void updateUserSettings(UserSettingsDto command, UserDetails userDetails) {
        UserSettings toUpdate = getUser(userDetails, User.class).getUserSettings();
        toUpdate.setWeightUnit(command.getWeightUnit());
        toUpdate.setLengthUnit(command.getLengthUnit());
        userSettingsPersistRepository.save(toUpdate);
    }

    private Set<Authority> getDefaultAuthoritiesForNewUser() {
        return authorityQueryRepository.findAllByDefaultAuthorityIsTrue();
    }
}
