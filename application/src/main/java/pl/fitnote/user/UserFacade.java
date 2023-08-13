package pl.fitnote.user;

import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.dto.CreateUserDto;
import pl.fitnote.user.dto.UpdateUserDto;
import pl.fitnote.user.dto.UserSettingsDto;

public interface UserFacade {
    Long createUser(CreateUserDto command, UserDetails userDetails);
    <T> T getUser(UserDetails userDetails, Class<T> type);
    void updateUser(UpdateUserDto command, UserDetails userDetails);
    void updateUserSettings(UserSettingsDto command, UserDetails userDetails);
}
