package pl.fitnote.user_session_utils;

import pl.fitnote.commons.CreateUserDetails;

record CreateLoggedUserDetails(String keycloakId, String email, String firstName, String lastName) implements CreateUserDetails {


    @Override
    public String getKeycloakId() {
        return keycloakId;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }
}
