package com.digibank.restapi.advice;

import com.digibank.restapi.exception.PinFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class PinExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = PinFailedException.class)
    public Map<String, Object> handleBadRequestException(PinFailedException ex) {
        return Map.of(
                "Count", ex.getMessage(),
                "message","Pin yang anda masukkan salah",
                "error", "True"
        );
    }
}
