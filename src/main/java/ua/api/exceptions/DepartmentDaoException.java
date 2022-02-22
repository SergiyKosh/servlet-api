package ua.api.exceptions;

public class DepartmentDaoException extends Exception {
    public DepartmentDaoException() {
        super();
    }

    public DepartmentDaoException(String message) {
        super(message);
    }

    public DepartmentDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentDaoException(Throwable cause) {
        super(cause);
    }

    protected DepartmentDaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
