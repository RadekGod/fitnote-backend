package pl.fitnote.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.fitnote.user.dto.CreateUserDto;
import pl.fitnote.user.dto.UpdateUserDto;
import pl.fitnote.user.dto.UserProjection;
import pl.fitnote.user.dto.UserSettingsDto;
import pl.fitnote.user_session_utils.SecurityContextUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
class UserController {

    private final UserFacade userFacade;

    @PostMapping()
    ResponseEntity<Long> createUser(@RequestBody CreateUserDto command) {
        return new ResponseEntity<>(userFacade.createUser(command, SecurityContextUtils.getDataForUserCreation()), HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<UserProjection> getUser() {
        return new ResponseEntity<>(userFacade.getUser(SecurityContextUtils.getLoggedUserDetails(), UserProjection.class), HttpStatus.OK);
    }

    @PutMapping()
    ResponseEntity<Void> updateUser(@RequestBody UpdateUserDto command) {
        userFacade.updateUser(command, SecurityContextUtils.getLoggedUserDetails());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/settings")
    ResponseEntity<Void> updateUserSettings(@RequestBody UserSettingsDto command) {
        userFacade.updateUserSettings(command, SecurityContextUtils.getLoggedUserDetails());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
