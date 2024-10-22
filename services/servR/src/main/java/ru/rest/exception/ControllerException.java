package ru.rest.exception;

public class ControllerException extends Exception {

    public ControllerException(String message, Exception cause) {
        super(message, cause);
    }
}
