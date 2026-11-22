package exceptions;

public class WithdrawalLimitExceededException extends AccountException {
    public WithdrawalLimitExceededException(String message) {
        super(message);
    }
}
