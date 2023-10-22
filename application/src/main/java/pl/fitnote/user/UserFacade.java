package pl.fitnote.user;

import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.dto.CreateUserDto;
import pl.fitnote.user.dto.UpdateUserDto;
import pl.fitnote.user.dto.UserSettingsDto;

public interface UserFacade {
    Long createUser(CreateUserDto command);
    <T> T getUser(UserDetails userDetails, Class<T> type);
    <T> T getUser(String email, Class<T> type);
    void updateUser(UpdateUserDto command, UserDetails userDetails);
    void updateUserSettings(UserSettingsDto command, UserDetails userDetails);
}
