package com.digibank.restapi.utils.ResponseOtp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandlerOtp {
    public static ResponseEntity<Object> generateResponseCreate(HttpStatus status, String message, Object res){
        Map<String,Object>map = new HashMap<>();
        map.put("status",status.value());
        map.put("message",message);
        map.put("data",res);
        return new ResponseEntity<>(map,status);
    }
}
