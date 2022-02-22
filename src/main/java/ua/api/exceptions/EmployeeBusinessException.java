package ua.api.exceptions;

public class EmployeeBusinessException extends Exception {
    public EmployeeBusinessException() {
        super();
    }

    public EmployeeBusinessException(String message) {
        super(message);
    }

    public EmployeeBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeBusinessException(Throwable cause) {
        super(cause);
    }

    protected EmployeeBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
