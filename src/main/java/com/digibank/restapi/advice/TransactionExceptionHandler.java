package com.digibank.restapi.advice;

import com.digibank.restapi.dto.response.transaction.detail.TransactionFailedDto;
import com.digibank.restapi.exception.TransactionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class TransactionExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TransactionNotFoundException.class)
    public TransactionFailedDto handleException(TransactionNotFoundException ex) {
        return TransactionFailedDto.builder()
                .error(true)
                .message(ex.getMessage())
                .build();
    }

//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(Exception.class)
//    public Map<String, Object> handleGlobalException(Exception ex) {
//        return Map.of(
//                "error", "An error occurred",
//                "message", ex.getMessage(),
//                "status", HttpStatus.INTERNAL_SERVER_ERROR.value()
//        );
//    }
}
