package dev.endribegaj.hospitalmanagementsystem.exception;

import jakarta.persistence.EntityExistsException;

public class UserNameExistException extends EntityExistsException {
    public UserNameExistException(String message) {
        super(message);
    }

    public UserNameExistException() {
        super("Username already exists");
    }
}
