package pl.fitnote.user_management.logic;

import pl.fitnote.user_management.command.CreateUserCommand;
import pl.fitnote.user_management.command.UserFormDto;
import pl.fitnote.user_management.model.user.User;
import pl.fitnote.user_management.model.user.vo.BirthDate;

public class UserFactory {

    public static User createUserDomainObject(CreateUserCommand createUserCommand) {
        UserFormDto userFormDto = createUserCommand.userFormDto();

        return User.builder()
                .birthDate(new BirthDate(userFormDto.birthDate()))
                .gender(userFormDto.gender())
                .build();
    }
}
