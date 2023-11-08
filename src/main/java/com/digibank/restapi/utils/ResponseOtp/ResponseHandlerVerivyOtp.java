package com.digibank.restapi.utils.ResponseOtp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandlerVerivyOtp {
    public static ResponseEntity<Object> generateResponseVerivyOtp(HttpStatus status, String message){
        Map<String,Object> map = new HashMap<>();
        map.put("status",status.value());
        map.put("message",message);
        return new ResponseEntity<>(map,status);
    }
}
