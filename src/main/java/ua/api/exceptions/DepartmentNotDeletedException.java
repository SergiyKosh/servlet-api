package ua.api.exceptions;

public class DepartmentNotDeletedException extends RuntimeException {
    public DepartmentNotDeletedException() {
        super("Could not to delete department");
    }

    public DepartmentNotDeletedException(String message) {
        super("Could not to delete department. " + message);
    }
}
