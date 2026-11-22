package exceptions;

public class AccountNotFoundException extends AccountException {
    private static final long serialVersionUID = 1L;

    public AccountNotFoundException(String message) {
        super(message);
    }
}
