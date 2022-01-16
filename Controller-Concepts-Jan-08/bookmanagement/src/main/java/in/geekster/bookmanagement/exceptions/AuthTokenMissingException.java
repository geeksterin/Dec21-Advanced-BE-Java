package in.geekster.bookmanagement.exceptions;

public class AuthTokenMissingException extends RuntimeException {

    public AuthTokenMissingException(final String message) {
        super(message);
    }
}
