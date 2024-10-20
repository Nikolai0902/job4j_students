package ru.serv_r.exception;

public class ControllerException extends Exception {

    public ControllerException(String message, Exception cause) {
        super(message, cause);
    }
}
