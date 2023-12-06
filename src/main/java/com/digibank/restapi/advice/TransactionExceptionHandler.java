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
}
