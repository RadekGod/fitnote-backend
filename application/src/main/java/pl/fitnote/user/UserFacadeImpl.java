package pl.fitnote.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fitnote.commons.UserDetails;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserPersistRepository userPersistRepository;
    private final UserQueryRepository userQueryRepository;
    private final UserSettingsPersistRepository userSettingsPersistRepository;
    private final UserFactory userFactory;

    @Override
    @Transactional
    public Long createUser(CreateUserDto toCreate, UserDetails userDetails) {
        User toSave = userFactory.createUserFromDto(toCreate);
        toSave.setKeycloakId(userDetails.getKeycloakId());
        toSave.setEmail(userDetails.getEmail());
        toSave.setEnabled(true);
        toSave.setCreationTime(Instant.now());
        return userPersistRepository.save(toSave).getId();
    }

    @Override
    public UserProjection getUser(final UserDetails userDetails) {
        return userQueryRepository.findByKeycloakId(userDetails.getKeycloakId(), UserProjection.class)
                .orElseThrow(() -> new EntityNotFoundException("User not found with given keycloakId"));
    }

    @Override
    @Transactional
    public void updateUser(UpdateUserDto command, UserDetails userDetails) {
        User toUpdate = userQueryRepository.findByKeycloakId(userDetails.getKeycloakId(), User.class)
                .orElseThrow(() -> new EntityNotFoundException("User not found with given keycloakId"));
        toUpdate.setFirstName(command.getFirstName());
        toUpdate.setLastName(command.getLastName());
        toUpdate.setBirthDate(command.getBirthDate());
        toUpdate.setGender(command.getGender());
        userPersistRepository.save(toUpdate);
    }

    @Override
    @Transactional
    public void updateUserSettings(UserSettingsDto command, UserDetails userDetails) {
        UserSettings toUpdate = userQueryRepository.findByKeycloakId(userDetails.getKeycloakId(), User.class)
                .orElseThrow(() -> new EntityNotFoundException("User not found with given keycloakId")).getUserSettings();
        toUpdate.setWeightUnit(command.getWeightUnit());
        toUpdate.setLengthUnit(command.getLengthUnit());
        userSettingsPersistRepository.save(toUpdate);
    }
}
