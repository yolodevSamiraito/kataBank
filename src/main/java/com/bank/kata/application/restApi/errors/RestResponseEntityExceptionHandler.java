package com.bank.kata.application.restApi.errors;


import com.bank.kata.application.service.error.AccountNotFoundException;
import com.bank.kata.application.service.error.InsufficientBalanceException;
import com.bank.kata.application.service.error.NegativeAmountException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ AccountNotFoundException.class })
    public ResponseEntity<Object> handleAccountNotFoundException(
            Exception ex) {
        return new ResponseEntity<>(
                ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ NegativeAmountException.class })
    public ResponseEntity<Object> handleNegativeAmountException(
            Exception ex) {
        return new ResponseEntity<>(
                ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ InsufficientBalanceException.class })
    public ResponseEntity<Object> handleInsufficientBalanceException(
            Exception ex) {
        return new ResponseEntity<>(
                ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
