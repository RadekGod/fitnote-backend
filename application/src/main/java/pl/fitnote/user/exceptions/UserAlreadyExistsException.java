package pl.fitnote.user.exceptions;

import lombok.AccessLevel;
import lombok.Getter;

public class UserAlreadyExistsException extends RuntimeException {
    @Getter(AccessLevel.PRIVATE)
    private static final String MESSAGE = "User already exists";

    UserAlreadyExistsException() {
        super(MESSAGE);
    }
}
