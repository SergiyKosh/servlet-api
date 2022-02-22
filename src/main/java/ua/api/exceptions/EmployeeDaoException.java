package ua.api.exceptions;

public class EmployeeDaoException extends Exception {
    public EmployeeDaoException() {
        super();
    }

    public EmployeeDaoException(String message) {
        super(message);
    }

    public EmployeeDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeDaoException(Throwable cause) {
        super(cause);
    }

    protected EmployeeDaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
