package com.digibank.restapi.exception.OtpException;

public class OtpFailedException extends RuntimeException {
    public OtpFailedException(String message) {
        super(message);
    }
}
