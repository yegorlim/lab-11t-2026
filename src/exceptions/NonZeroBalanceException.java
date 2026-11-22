package exceptions;

public class NonZeroBalanceException extends AccountException {
    private static final long serialVersionUID = 1L;

    public NonZeroBalanceException(String message) {
        super(message);
    }
}
