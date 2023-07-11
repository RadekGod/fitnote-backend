package pl.fitnote.user_management;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import pl.fitnote.user_management.model.user.User;
import pl.fitnote.user_management.model.user.vo.Gender;

import java.sql.Date;
import java.time.Instant;

class UserFactory {

    public static UserEntity fromDomain(User user) {
        return UserEntity.builder()
                .keycloakId(user.getKeycloakId().value())
                .username(user.getUsername().value())
                .email(user.getEmail().value())
                .creationTime(user.getCreationTime().value())
                .enabled(user.getEnabled().value())
                .firstName(user.getFirstName().value())
                .lastName(user.getLastName().value())
                .birthDate(user.getBirthDate().value())
                .gender(user.getGender())
                .build();
    }
}
