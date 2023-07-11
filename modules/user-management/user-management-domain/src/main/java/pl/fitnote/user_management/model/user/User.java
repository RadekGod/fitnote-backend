package pl.fitnote.user_management.model.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.fitnote.user_management.model.user.vo.*;

@Builder
@Getter
@Setter
public class User {
    private UserId userId;
    private KeycloakId keycloakId;
    private Email email;
    private Username username;
    private CreationTime creationTime;
    private Enabled enabled;
    private FirstName firstName;
    private LastName lastName;
    private BirthDate birthDate;
    private Gender gender;
}
