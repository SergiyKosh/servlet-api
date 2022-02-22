package ua.api.exceptions;

public class DepartmentNotUpdatedException extends RuntimeException {
    public DepartmentNotUpdatedException(String message) {
        super("Could not to update department. " + message);
    }



    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
