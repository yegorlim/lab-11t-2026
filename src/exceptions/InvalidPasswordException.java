package exceptions;

public class InvalidPasswordException extends AccountException {
    private static final long serialVersionUID = 1L;

    public InvalidPasswordException(String message) {
        super(message);
    }
}
