package in.geekster.bookmanagement.exceptions;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(final String message) {
        super(message);
    }
}
