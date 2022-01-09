package in.geekster.bookmanagement.exceptions;

public class AuthTokenMissingException extends Exception {

    public AuthTokenMissingException(final String message) {
        super(message);
    }
}
