package com.Bank_Details.exception;

import org.springframework.http.HttpStatus;

public class BankApiException extends RuntimeException{
    private HttpStatus status;
    private String message;


    public BankApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
