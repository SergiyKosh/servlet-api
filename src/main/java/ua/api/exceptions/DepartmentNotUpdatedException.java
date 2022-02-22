package ua.api.exceptions;

public class DepartmentNotUpdatedException extends RuntimeException {
    public DepartmentNotUpdatedException() {
        super("Could not to update department");
    }

    public DepartmentNotUpdatedException(String message) {
        super("Could not to update department. " + message);
    }
}
