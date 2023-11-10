package com.digibank.restapi.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseCifHandler {

    public static ResponseEntity<Object> generateResponseCif(String message, HttpStatus status, String norek) {

        Map<String, Object>map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("norek", norek);

        return new ResponseEntity<>(map, status);

    }

    public static ResponseEntity<Object> generateResponseCreateRekening(String message, HttpStatus status) {

        Map<String, Object>map = new HashMap<>();
        map.put("message", message);
        map.put("status", status);

        return new ResponseEntity<>(map, status);

    }

}
