package com.digibank.restapi.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponseCif(String message, HttpStatus status, String norek) {

        Map<String, Object>map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("norek", norek);

        return new ResponseEntity<>(map, status);

    }

    public static ResponseEntity<Object> loginResponse(String message, HttpStatus status, Object res){
        return getObjectResponseEntity(status, message, res);
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

    public static ResponseEntity<Object> generateResponseCreate(HttpStatus status, String message, Object res){
        return getObjectResponseEntity(status, message, res);
    }

    @NotNull
    public static ResponseEntity<Object> getObjectResponseEntity(HttpStatus status, String message, Object res) {
        Map<String,Object> map = new HashMap<>();
        map.put("status",status.value());
        map.put("message",message);
        map.put("data",res);
        return new ResponseEntity<>(map,status);
    }

    public static ResponseEntity<Object> generateResponseVerivyOtp(HttpStatus status, String message){
        Map<String,Object> map = new HashMap<>();
        map.put("status",status.value());
        map.put("message",message);
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
    public static ResponseEntity<Object> generateResponseCreate(String message, HttpStatus status, Object res){
        Map<String,Object> map = new HashMap<>();
        map.put("message",message);
        map.put("status",status.value());
        map.put("data",res);
        return new ResponseEntity<>(map,status);
    }

    public static ResponseEntity<Object> getAccounts(String message, HttpStatus status, Object res){
        Map<String,Object> map = new HashMap<>();
        map.put("message",message);
        map.put("status",status.value());
        map.put("data",res);
        return new ResponseEntity<>(map,status);
    }
    public static ResponseEntity<Object> generateTransferAntarBank(Object res,Object res2){
        Map<String,Object> map = new HashMap<>();
        map.put("Bank",res);
        map.put("pengirim",res2);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
}