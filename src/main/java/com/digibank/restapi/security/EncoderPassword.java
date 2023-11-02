package com.digibank.restapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class EncoderPassword {

    @Bean
    public PasswordEncoder encode(){
        return new BCryptPasswordEncoder();
    }
}
