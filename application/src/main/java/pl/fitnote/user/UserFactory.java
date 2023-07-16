package pl.fitnote.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

//    UserDto fromEntity(User source) {
//        return UserDto.builder()
//                .id(source.getId())
//                .keycloakId(source.getKeycloakId())
//                .email(source.getEmail())
//                .creationTime(source.getCreationTime())
//                .enabled(source.getEnabled())
//                .firstName(source.getFirstName())
//                .lastName(source.getLastName())
//                .birthDate(source.getBirthDate())
//                .gender(source.getGender())
//                .userSettings(fromDomain(source.getUserSettings()))
//                .build();
//    }
}
