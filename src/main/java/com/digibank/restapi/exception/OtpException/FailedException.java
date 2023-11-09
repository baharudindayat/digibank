package com.digibank.restapi.exception.OtpException;

public class FailedException extends RuntimeException {
    public FailedException(String message) {
        super(message);
    }
}
