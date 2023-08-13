package pl.fitnote.user_session_utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import pl.fitnote.commons.CreateUserDetails;
import pl.fitnote.commons.UserDetails;

public class SecurityContextUtils {

    private SecurityContextUtils() {

    }
    public static String getLoggedUserKeycloakId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static String getLoggedUserEmail() {
        return ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getClaims().get("email").toString();
    }

    public static String getLoggedUserFirstName() {
        return ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getClaims().get("given_name").toString();
    }

    public static String getLoggedUserLastName() {
        return ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getClaims().get("family_name").toString();
    }

    public static CreateUserDetails getDataForUserCreation() {
        return new CreateLoggedUserDetails(getLoggedUserKeycloakId(), getLoggedUserEmail(), getLoggedUserFirstName(), getLoggedUserLastName());
    }

    public static UserDetails getLoggedUserDetails() {
        return new LoggedUserDetails(getLoggedUserKeycloakId(), getLoggedUserEmail());
    }
}
