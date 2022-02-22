package ua.api.exceptions;

public class EmployeeNotAddedException extends RuntimeException {
    public EmployeeNotAddedException() {
        super("Could not to add employee");
    }

    public EmployeeNotAddedException(String message) {
        super("Could not to add employee. " + message);
    }
}
