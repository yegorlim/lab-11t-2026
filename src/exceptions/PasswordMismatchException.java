package exceptions;

public class PasswordMismatchException extends AccountException {
    private static final long serialVersionUID = 1L;

    public PasswordMismatchException(String message) {
        super(message);
    }
}
