package pl.fitnote.user.dto;

import pl.fitnote.user.Gender;

import java.sql.Date;
import java.time.Instant;

public interface UserProjection {
    Long getId();
    String getKeycloakId();
    String getEmail();
    Instant getCreationTime();
    Boolean getEnabled();
    String getFirstName();
    String getLastName();
    Date getBirthDate();
    Gender getGender();
    UserSettingsProjection getUserSettings();
}
