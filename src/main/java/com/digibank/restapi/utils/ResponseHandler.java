package com.digibank.restapi.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponseCreate(String message, HttpStatus status, Object res){
        Map<String,Object> map = new HashMap<>();
        map.put("message",message);
        map.put("status",status.value());
        map.put("data",res);
        return new ResponseEntity<>(map,status);
    }

    public static ResponseEntity<Object> generateResponseTransfer(String message, HttpStatus status,Object res, Object res2,Object res3){
        Map<String,Object> map = new HashMap<>();
        map.put("message",message);
        map.put("error", "false");
        map.put("data",res);
        map.put("pengirim",res2);
        map.put("penerima",res3);
        return new ResponseEntity<>(map,status);
    }
}
