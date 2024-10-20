package ru.serv_s.exception;

public class ServiceException extends Exception {

    public ServiceException(String message, Exception cause) {
        super(message, cause);
    }
}
