package com.bank.kata.application.service.error;

public class InsufficientBalanceException extends Exception {

    private static final long serialVersionUID = 7657622788569863045L;

    public InsufficientBalanceException() {
        super();
    }

    public InsufficientBalanceException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InsufficientBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }

    public InsufficientBalanceException(Throwable cause) {
        super(cause);
    }
}
