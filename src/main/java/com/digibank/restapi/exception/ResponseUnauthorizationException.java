package com.digibank.restapi.exception;


public class ResponseUnauthorizationException extends RuntimeException {
    public ResponseUnauthorizationException(String message) {
        super(message);
    }

}
