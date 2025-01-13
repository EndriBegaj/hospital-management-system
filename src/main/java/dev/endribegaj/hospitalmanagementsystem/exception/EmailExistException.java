package dev.endribegaj.hospitalmanagementsystem.exception;

import jakarta.persistence.EntityExistsException;

public class EmailExistException extends EntityExistsException {
    public EmailExistException(String message) {
        super(message);
    }

    public EmailExistException() {
        super("Email already exists");
    }
}
