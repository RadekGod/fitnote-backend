package pl.fitnote.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class CreateUserDto {
    private String  firstName;
    private String  lastName;
    private Date birthDate;
    private Gender gender;
    private UserSettingsDto userSettingsDto;
}
