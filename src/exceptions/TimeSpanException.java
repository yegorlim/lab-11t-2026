package exceptions;

public class TimeSpanException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TimeSpanException(String message) {
        super(message);
    }
}
