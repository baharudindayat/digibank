package com.digibank.restapi.config;

import com.digibank.restapi.mapper.transfer.TransferObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    @Bean
    public TransferObjectMapper transferObjectMapper(){
        return new TransferObjectMapper();
    }
}
