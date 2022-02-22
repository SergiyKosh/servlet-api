package ua.api.exceptions;

public class EmployeeNotUpdatedException extends RuntimeException {
    public EmployeeNotUpdatedException() {
        super("Could not to update employee");
    }

    public EmployeeNotUpdatedException(String message) {
        super("Could not to update employee. " + message);
    }
}
