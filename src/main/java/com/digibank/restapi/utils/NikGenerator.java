package com.digibank.restapi.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class NikGenerator {
    private final AtomicLong counter = new AtomicLong(1);

    public String generateNik() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = dateFormat.format(new Date());
        String counterPart = String.format("%06d", counter.getAndIncrement() % 1000000);

        return date + counterPart;
    }
}
