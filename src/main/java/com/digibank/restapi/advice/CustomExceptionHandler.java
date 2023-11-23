package com.digibank.restapi.advice;

import com.digibank.restapi.exception.NikNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NikNotFoundException.class)
    public Map<String, Object> handleNikNotFoundException(NikNotFoundException ex) {
        return Map.of(
                "message", ex.getMessage(),
                "status", HttpStatus.NOT_FOUND
        );
    }
}
