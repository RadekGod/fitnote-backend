package pl.fitnote.user_management;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
class UserManagementController {

@GetMapping("test")
    String testUser() {
    return "Działa";
}

}
