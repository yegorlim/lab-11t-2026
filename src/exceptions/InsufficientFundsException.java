package exceptions;

public class InsufficientFundsException extends AccountException {
    private static final long serialVersionUID = 1L;

    public InsufficientFundsException(String message) {
        super(message);
    }
}
