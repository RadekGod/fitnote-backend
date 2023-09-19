package pl.fitnote.commons.user_session_utils;

import pl.fitnote.commons.UserDetails;

record LoggedUserDetails(String email) implements UserDetails {

    @Override
    public String getEmail() {
        return email;
    }
}
