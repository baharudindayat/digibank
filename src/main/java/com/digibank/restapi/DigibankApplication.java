package com.digibank.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DigibankApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigibankApplication.class, args);
	}

}
