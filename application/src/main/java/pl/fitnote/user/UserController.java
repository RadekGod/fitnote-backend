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
import pl.fitnote.user_session_utils.SecurityContextUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
class UserController {

    private final UserFacade userFacade;

    @PostMapping()
    ResponseEntity<Long> createUser(@RequestBody CreateUserDto toCreate) {
        return new ResponseEntity<>(userFacade.createUser(toCreate, SecurityContextUtils.getDataForUserCreation()), HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<UserProjection> getUser() {
        return new ResponseEntity<>(userFacade.getUser(SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @PutMapping()
    ResponseEntity<Void> updateUser(@RequestBody UpdateUserDto toUpdate) {
        userFacade.updateUser(toUpdate, SecurityContextUtils.getLoggedUserDetails());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/settings")
    ResponseEntity<Void> updateUserSettings(@RequestBody UserSettingsDto toUpdate) {
        userFacade.updateUserSettings(toUpdate, SecurityContextUtils.getLoggedUserDetails());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
