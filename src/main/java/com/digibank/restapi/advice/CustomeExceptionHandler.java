package com.digibank.restapi.advice;


import com.digibank.restapi.exception.LoginFailedException;
import jakarta.servlet.FilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.Map;

@RestControllerAdvice
public class CustomeExceptionHandler {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = LoginFailedException.class)
    public Map<String, Object> handleLoginException(LoginFailedException ex) {
        return Map.of(
                "message", ex.getMessage(),
                "status", HttpStatus.UNAUTHORIZED.value()
        );
    }

}
