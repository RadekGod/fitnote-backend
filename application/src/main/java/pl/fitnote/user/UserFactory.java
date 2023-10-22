package pl.fitnote.user;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.fitnote.user.dto.CreateUserDto;

import java.time.Instant;

@Service
@RequiredArgsConstructor
class UserFactory {

    private final UserSettingsFactory userSettingsFactory;
    private final PasswordEncoder passwordEncoder;

    User createUserFromDto(CreateUserDto source) {

        return User.builder()
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .email(source.getEmail())
                .password(passwordEncoder.encode(source.getPassword()))
                .birthDate(source.getBirthDate())
                .gender(source.getGender())
                .enabled(true)
                .creationTime(Instant.now())
                .userSettings(userSettingsFactory.createDefaultSettingsForNewUser())
                .build();
    }
}
