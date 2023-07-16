package pl.fitnote.user;

import java.sql.Date;
import java.time.Instant;

interface UserProjection {
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
