package exceptions;

public class AuthenticationException extends AccountException {
    public AuthenticationException(String message) {
        super(message);
    }
}
