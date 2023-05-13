package pl.fitnote.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {

    @GetMapping("/anonymous")
    public String getNotices() {

            return "anonymous";
    }

    @GetMapping("/admin")
    public String getAdmin() {

        return "admin";
    }

    @GetMapping("/user")
    public String getUser() {

        return "user";
    }

}
