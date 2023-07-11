package pl.fitnote.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class NoticesController {

    @GetMapping("/anonymous")
    public String getNotices() {

        return "anonymous";
    }

    @GetMapping("/user")
    public String getUser() {

        return "user";
    }

    @GetMapping("/admin")
    public String getAdmin() {

        return "admin";
    }

}
