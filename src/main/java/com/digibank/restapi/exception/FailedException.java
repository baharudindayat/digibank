package com.digibank.restapi.exception;

public class FailedException extends RuntimeException {
    public FailedException(String message) {
        super(message);
    }
}
