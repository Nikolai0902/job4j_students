package ru.rest.exception;

public class ServiceException extends Exception {

    public ServiceException(String message, Exception cause) {
        super(message, cause);
    }
}
