package pl.fitnote.user;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.fitnote.user.dto.CreateUserDto;
import pl.fitnote.user.dto.UpdateUserDto;
import pl.fitnote.user.dto.UserProjection;
import pl.fitnote.user.dto.UserSettingsDto;
import pl.fitnote.commons.userSessionUtils.SecurityContextUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping()
class UserController {

    private final UserFacade userFacade;

    @PostMapping("/register")
    ResponseEntity<?> createUser(@RequestBody CreateUserDto command) {
        try {
            return new ResponseEntity<>(userFacade.createUser(command), HttpStatus.CREATED);
        } catch (EntityExistsException exception) {
            return new ResponseEntity<>("User already exists with given email", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<UserProjection> getUserDetailsAfterLogin() {
        try {
            return new ResponseEntity<>(userFacade.getUser(SecurityContextUtils.getLoggedUserDetails(), UserProjection.class), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with given E-mail");
        }
    }

    @PutMapping("/user")
    ResponseEntity<Void> updateUser(@RequestBody UpdateUserDto command) {
        userFacade.updateUser(command, SecurityContextUtils.getLoggedUserDetails());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/user/settings")
    ResponseEntity<Void> updateUserSettings(@RequestBody UserSettingsDto command) {
        userFacade.updateUserSettings(command, SecurityContextUtils.getLoggedUserDetails());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
