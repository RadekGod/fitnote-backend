package pl.fitnote.user.exceptions;

import lombok.AccessLevel;
import lombok.Getter;

public class UserNotFoundException extends RuntimeException {
    @Getter(AccessLevel.PRIVATE)
    private static final String MESSAGE = "User not found with given E-mail";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
