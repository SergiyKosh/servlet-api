package ua.api.exceptions;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(String id) {
        super("Department with id = " + id + " not found");
    }
}
