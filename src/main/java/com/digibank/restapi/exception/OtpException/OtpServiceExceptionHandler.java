package com.digibank.restapi.exception.OtpException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class OtpServiceExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<OtpResponse> handleException(Exception e) {
        OtpResponse response = new OtpResponse();
        if (e.getMessage().equals("Email Sudah Terdaftar")) {
            response.setStatus(400);
            response.setMessage("Email Sudah Terdaftar");
        } else {
            response.setStatus(200);
            response.setId_user("1");
            response.setEmail("budi@gmail.com");
            response.setOtp("1990");
        }
        return ResponseEntity.status(HttpStatus.valueOf(response.getStatus())).body(response);
    }
}

