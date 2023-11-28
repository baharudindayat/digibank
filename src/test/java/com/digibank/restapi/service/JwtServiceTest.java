package com.digibank.restapi.service;

import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.model.enums.AccountStatus;
import com.digibank.restapi.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class JwtServiceTest {


    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Value("${token.login.key}")
    private String jwtLoginKey;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setEmail("fahri@gmail.com");
        user.setPassword("$2y$10$3YzNIboVlFXGZkjzdsswLuEM.XOBRWAMCtFNGBKgZ7UisIykPTtqy");
        user.setStatusUser(AccountStatus.ACTIVE);
        user.setMpin("898725");
        user.setActive(true);
        user.setCountBlockedMpin(0);
        userRepository.save(user);
    }

    @Test
    public void testTokenExpiration() {

        Optional<User> user = userRepository.findByEmail("kevin@gmail.com");
        log.info("mylog: " + user.get().getMpin());

        String token = Jwts.builder()
                .setSubject(user.get().getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60  * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtLoginKey)
                .compact();
        log.info("myToken: " + token);

        assertTrue(jwtService.isTokenValid(token, user.get()));
    }


}