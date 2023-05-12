package ru.dakon.MoexService.exception;

public class LimitRequestsException extends RuntimeException {
    public LimitRequestsException(String message) {
        super(message);
    }
}
