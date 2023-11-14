package com.digibank.restapi.exception;

public class ResponseRequestTimeoutException extends RuntimeException{
    public ResponseRequestTimeoutException(String message) {
        super(message);
    }
}
