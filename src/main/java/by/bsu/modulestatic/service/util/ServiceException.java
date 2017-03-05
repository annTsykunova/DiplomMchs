package by.bsu.modulestatic.service.util;


public class ServiceException extends Exception {
    public ServiceException(Exception ex) {
        super(ex);
    }

    public ServiceException(String message) {
        super(message);
    }
    public ServiceException(String message,Exception ex) {
        super(message,ex);
    }
}
