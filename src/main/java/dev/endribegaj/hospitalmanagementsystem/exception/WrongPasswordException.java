package dev.endribegaj.hospitalmanagementsystem.exception;
public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String message) {
        super(message);
    }

    public WrongPasswordException() {
        super("Wrong password");
    }
}
