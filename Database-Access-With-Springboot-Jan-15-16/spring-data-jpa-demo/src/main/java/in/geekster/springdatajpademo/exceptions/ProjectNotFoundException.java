package in.geekster.springdatajpademo.exceptions;

public class ProjectNotFoundException extends Exception {

    public ProjectNotFoundException(final String message) {
        super(message);
    }
}
