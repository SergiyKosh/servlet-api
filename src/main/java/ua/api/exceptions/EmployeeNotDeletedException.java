package ua.api.exceptions;

public class EmployeeNotDeletedException extends RuntimeException {
    public EmployeeNotDeletedException() {
        super("Could not to delete employee");
    }

    public EmployeeNotDeletedException(String message) {
        super("Could not to delete employee. " + message);
    }
}
