package ua.api.exceptions;

public class DepartmentBusinessException extends Exception {
    public DepartmentBusinessException() {
        super();
    }

    public DepartmentBusinessException(String message) {
        super(message);
    }

    public DepartmentBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentBusinessException(Throwable cause) {
        super(cause);
    }

    protected DepartmentBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
