package pl.fitnote.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fitnote.user.Gender;

import java.sql.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
    private String  firstName;
    private String  lastName;
    private String  email;
    private String  password;
    private Date birthDate;
    private Gender gender;
    private UserSettingsDto userSettingsDto;
}
