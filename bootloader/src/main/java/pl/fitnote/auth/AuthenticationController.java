package pl.fitnote.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.fitnote.user.UserFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class AuthenticationController {

    private final UserFacade userFacade;
//    @PostMapping("/register")
//    ResponseEntity<?> createUser(@RequestBody CreateUserDto command) {
//        try {
//            return new ResponseEntity<>(userFacade.createUser(command), HttpStatus.CREATED);
//        } catch (EntityExistsException exception) {
//            return new ResponseEntity<>("User already exists with given email", HttpStatus.CONFLICT);
//        }

//    }

//    @GetMapping("/login")
//    public UserProjection getUserDetailsAfterLogin() {
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getDetails());
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
//        return userFacade.getUser(SecurityContextHolder.getContext().getAuthentication().getName(), UserProjection.class);
//    }



}