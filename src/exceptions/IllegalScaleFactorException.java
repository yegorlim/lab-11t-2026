package exceptions;

public class IllegalScaleFactorException extends TimeSpanException {
    private static final long serialVersionUID = 1L;

    public IllegalScaleFactorException(String message) {
        super(message);
    }
}
