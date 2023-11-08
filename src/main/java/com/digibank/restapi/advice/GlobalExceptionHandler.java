package com.digibank.restapi.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> handleException(Exception ex) {
        return Map.of(
                "message", ex.getMessage(),
                "error", "True",
                "Status", HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}
