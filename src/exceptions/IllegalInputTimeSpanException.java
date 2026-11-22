package exceptions;

public class IllegalInputTimeSpanException extends TimeSpanException {
    private static final long serialVersionUID = 1L;

    public IllegalInputTimeSpanException(String message) {
        super(message);
    }
}
