package com.digibank.restapi.exception;

import lombok.Getter;

@Getter
public class PinFailedException extends RuntimeException{
        public PinFailedException(String message) {
            super(message);
        }
}
