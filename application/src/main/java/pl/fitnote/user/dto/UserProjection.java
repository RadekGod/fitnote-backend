package pl.fitnote.user.dto;

import pl.fitnote.user.Gender;

import java.sql.Date;
import java.time.Instant;
import java.util.Set;

public interface UserProjection {
    Long getId();
    String getEmail();
    Instant getCreationTime();
    Boolean getEnabled();
    String getFirstName();
    String getLastName();
    Date getBirthDate();
    Gender getGender();
    Set<AuthorityProjection> getUserAuthorities();
    UserSettingsProjection getUserSettings();
}
