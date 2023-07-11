package pl.fitnote.user_management.command;

import pl.fitnote.user_management.model.user.vo.Gender;

import java.sql.Date;
import java.time.Instant;

public record UserFormDto(
//        String username,
        Date birthDate,
        Gender gender
) {
}
