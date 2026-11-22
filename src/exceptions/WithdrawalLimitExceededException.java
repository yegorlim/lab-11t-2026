package exceptions;

public class WithdrawalLimitExceededException extends AccountException {
    private static final long serialVersionUID = 1L;

    public WithdrawalLimitExceededException(String message) {
        super(message);
    }
}
