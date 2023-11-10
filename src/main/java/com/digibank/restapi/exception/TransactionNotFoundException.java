package com.digibank.restapi.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class TransactionNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public TransactionNotFoundException(String message) {
        super(message);
    }
}
