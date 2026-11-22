package exceptions;

public class PasswordMismatchException extends AccountException {
    public PasswordMismatchException(String message) {
        super(message);
    }
}
