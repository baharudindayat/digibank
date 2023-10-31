package com.digibank.restapi;

import com.digibank.restapi.digibank.entity.data.tipe_rekeningDATA;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DigibankApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigibankApplication.class, args);
	}

	@Bean
	public ApplicationRunner dataInitializerRunner(tipe_rekeningDATA tipeRekeningDATA) {
		return args -> {
			tipeRekeningDATA.initData();
		};
	}

}
