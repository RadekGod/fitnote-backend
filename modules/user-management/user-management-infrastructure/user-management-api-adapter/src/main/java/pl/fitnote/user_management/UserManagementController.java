package pl.fitnote.user_management;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.fitnote.user_management.command.CreateUserCommand;
import pl.fitnote.user_management.command.UserFormDto;
import pl.fitnote.user_management.model.UserIdResponse;
import pl.fitnote.user_management.port.incoming.CreateUserPort;
import pl.fitnote.user_session_utils.SecurityContextUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
class UserManagementController {

    private final CreateUserPort createUserPort;
@PostMapping()
    ResponseEntity<UserIdResponse> createUser(@RequestBody UserFormDto userFormDto) {
    CreateUserCommand createUserCommand = new CreateUserCommand(userFormDto);
    System.out.println("test");
    System.out.println(createUserCommand);
    return new ResponseEntity<>(new UserIdResponse(createUserPort.createUser(createUserCommand, SecurityContextUtils.getDataForUserCreation())), HttpStatus.OK);
}

}
