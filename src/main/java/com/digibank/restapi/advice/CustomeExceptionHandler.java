package com.digibank.restapi.advice;


import com.digibank.restapi.exception.ResponseBadRequestException;
import com.digibank.restapi.exception.ResponseRequestTimeoutException;
import com.digibank.restapi.exception.ResponseUnauthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class CustomeExceptionHandler {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ResponseUnauthorizationException.class)
    public Map<String, Object> handleUnauthorizedException(ResponseUnauthorizationException ex) {
        return Map.of(
                "message", ex.getMessage(),
                "status", HttpStatus.UNAUTHORIZED.value()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ResponseBadRequestException.class)
    public Map<String, Object> handleBadRequestException(ResponseBadRequestException ex) {
        return Map.of(
                "message", ex.getMessage(),
                "status", HttpStatus.BAD_REQUEST.value()
        );
    }

    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    @ExceptionHandler(value = ResponseRequestTimeoutException.class)
    public Map<String, Object> handleRequestTimeoutException(ResponseRequestTimeoutException ex) {
        return Map.of(
                "message", ex.getMessage(),
                "status", HttpStatus.REQUEST_TIMEOUT.value()
        );
    }



}
