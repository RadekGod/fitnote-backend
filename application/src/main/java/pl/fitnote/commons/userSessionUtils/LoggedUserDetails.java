package pl.fitnote.commons.userSessionUtils;

import pl.fitnote.commons.UserDetails;

record LoggedUserDetails(String email) implements UserDetails {

    @Override
    public String getEmail() {
        return email;
    }
}
