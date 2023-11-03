package com.digibank.restapi.advice;

import com.digibank.restapi.exception.TransferFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class TransferExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = TransferFailedException.class)
    public Map<String, Object> handleRekeningNotFoundException(TransferFailedException ex) {
        return Map.of(
                "message", ex.getMessage(),
                "status", HttpStatus.NOT_FOUND
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = TransferFailedException.class)
    public Map<String, Object> handleBadRequestException(TransferFailedException ex) {
        return Map.of(
                "message", ex.getMessage(),
                "status", HttpStatus.BAD_REQUEST
        );
    }


}
