package pl.fitnote.user;

import pl.fitnote.commons.UserDetails;

interface UserFacade {
    Long createUser(CreateUserDto toCreate, UserDetails userDetails);
    UserProjection getUser(UserDetails userDetails);
    void updateUser(UpdateUserDto toUpdate, UserDetails userDetails);
    void updateUserSettings(UserSettingsDto toUpdate, UserDetails userDetails);
}
