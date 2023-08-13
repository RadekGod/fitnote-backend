package pl.fitnote.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fitnote.user.dto.CreateUserDto;
import pl.fitnote.user.dto.UserProjection;

@Service
@RequiredArgsConstructor
class UserFactory {

    private final UserSettingsFactory userSettingsFactory;

    User createUserFromDto(CreateUserDto source) {
        System.out.println(source);
        return User.builder()
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .birthDate(source.getBirthDate())
                .gender(source.getGender())
                .userSettings(userSettingsFactory.fromDto(source.getUserSettingsDto()))
                .build();
    }

    User fromProjection(UserProjection source) {
        return User.builder()
                .id(source.getId())
                .keycloakId(source.getKeycloakId())
                .email(source.getEmail())
                .creationTime(source.getCreationTime())
                .enabled(source.getEnabled())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .birthDate(source.getBirthDate())
                .gender(source.getGender())
                .userSettings(userSettingsFactory.fromProjection(source.getUserSettings()))
                .build();
    }
}
