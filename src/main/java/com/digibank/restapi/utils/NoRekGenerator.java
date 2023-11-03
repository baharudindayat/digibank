package com.digibank.restapi.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class NoRekGenerator {

    private final AtomicLong counter = new AtomicLong(1);

    public String generateNoRek() {
        String fixedPrefix = "77123456";
        String uniquePart = String.format("%08d", counter.getAndIncrement() % 100000000);

        return fixedPrefix + uniquePart;
    }

}