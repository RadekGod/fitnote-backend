package pl.fitnote.commons.userSessionUtils;

import org.springframework.security.core.context.SecurityContextHolder;
import pl.fitnote.commons.UserDetails;

public class SecurityContextUtils {

    private SecurityContextUtils() {

    }
    public static String getLoggedUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }



    public static UserDetails getLoggedUserDetails() {
        return new LoggedUserDetails(getLoggedUserEmail());
    }
}
