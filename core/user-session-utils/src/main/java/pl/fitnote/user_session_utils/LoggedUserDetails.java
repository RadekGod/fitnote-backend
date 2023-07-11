package pl.fitnote.user_session_utils;

import pl.fitnote.commons.UserDetails;

record LoggedUserDetails(String keycloakId, String email) implements UserDetails {

    @Override
    public String getKeycloakId() {
        return keycloakId;
    }

    @Override
    public String getEmail() {
        return email;
    }
}
