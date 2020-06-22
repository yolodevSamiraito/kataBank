package com.bank.kata.application.service.error;

public class NegativeAmountException extends Exception {

    public NegativeAmountException() {
        super();
    }

    public NegativeAmountException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NegativeAmountException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativeAmountException(String message) {
        super(message);
    }

    public NegativeAmountException(Throwable cause) {
        super(cause);
    }
}
