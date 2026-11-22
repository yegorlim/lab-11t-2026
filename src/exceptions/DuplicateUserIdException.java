package exceptions;

public class DuplicateUserIdException extends AccountException {
    private static final long serialVersionUID = 1L;

    public DuplicateUserIdException(String message) {
        super(message);
    }
}
