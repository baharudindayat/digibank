package com.digibank.restapi;

import com.digibank.restapi.digibank.model.data.TipeRekeningData;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.digibank.restapi.digibank.entity")
@EnableJpaRepositories("com.digibank.restapi.digibank.repository")
@SpringBootApplication
public class DigibankApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigibankApplication.class, args);
	}

	@Bean
	public ApplicationRunner dataInitializerRunner(TipeRekeningData tipeRekeningDATA) {
		return args -> {
			tipeRekeningDATA.initData();
		};
	}

}
