package exceptions;

public class NegativeTimeSpanException extends TimeSpanException {
    private static final long serialVersionUID = 1L;

    public NegativeTimeSpanException(String message) {
        super(message);
    }
}
