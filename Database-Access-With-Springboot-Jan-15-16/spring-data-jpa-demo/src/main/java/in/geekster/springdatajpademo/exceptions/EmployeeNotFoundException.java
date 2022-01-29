package in.geekster.springdatajpademo.exceptions;

public class EmployeeNotFoundException extends Exception {

    public EmployeeNotFoundException(final String message) {
        super(message);
    }
}
