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

    public static ResponseEntity<Object> getTypeRekening(Object res){
        Map<Object, Object> map = new HashMap<>();
        map.put("data",res);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public static ResponseEntity<Object> createMpin(String message, HttpStatus status){
        Map<String,Object> map = new HashMap<>();
        map.put("message",message);
        map.put("status",status.value());
        return new ResponseEntity<>(map,status);
    }
}
