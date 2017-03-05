package by.bsu.modulestatic.dao.util;

public class DaoException extends Exception {
    public DaoException(Exception ex) {
        super(ex);
    }

    public DaoException(String message) {
        super(message);
    }
    public DaoException(String message,Exception ex) {
        super(message,ex);
    }
}
