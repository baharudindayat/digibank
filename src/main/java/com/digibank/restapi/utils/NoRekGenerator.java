package com.digibank.restapi.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NoRekGenerator {
    public String generateRekening() {
        Random random = new Random();
        int randomNumber = 10000000 + random.nextInt(90000000);
        String fixedPrefix = "77123456";
        String uniquePart = String.format("%08d", randomNumber);

        return fixedPrefix + uniquePart;
    }
}