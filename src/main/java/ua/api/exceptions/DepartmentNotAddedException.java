package ua.api.exceptions;

public class DepartmentNotAddedException extends RuntimeException {
    public DepartmentNotAddedException() {
        super("Could not to add department");
    }

    public DepartmentNotAddedException(String message) {
        super("Could not to add department. " + message);
    }
}
