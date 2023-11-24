package com.digibank.restapi.controller;

import com.digibank.restapi.model.entity.CIF;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.model.entity.UserOTP;
import com.digibank.restapi.model.enums.AccountStatus;
import com.digibank.restapi.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.sql.Timestamp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private CifRepository cifRepository;

    @Autowired
    private RekeningRepository rekeningRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserOtpRepository userOtpRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        transactionsRepository.deleteAll();
        rekeningRepository.deleteAll();
        cifRepository.deleteAll();
        userRepository.deleteAll();

        UserOTP userOTP = new UserOTP();
        userOTP.setIdOtp(1L);
        userOTP.setIdUser(userOTP.getIdUser());
        userOTP.setOtp("1234");
        userOTP.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userOtpRepository.save(userOTP);

        User user = new User();
        user.setIdUser(1L);
        user.setStatusUser(AccountStatus.valueOf("ACTIVE"));
        user.setEmail("example@gmail.com");
        user.setActive(Boolean.valueOf("true"));
        user.setPassword("123");
        user.setMpin("123456");
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);

    }

    @Test
    void createCifSuccess() throws Exception {
        User user = new User();
        user.setEmail("ilham@gmail.com");
        user.setPassword("asd");
        user.setStatusUser(AccountStatus.ACTIVE);
        user.setMpin("898725");
        user.setActive(true);
        user.setCountBlockedMpin(0);
        userRepository.save(user);

        CIF request = new CIF();
        request.setIdUsers(user);
        request.setNik("1234567890");
        request.setNamaLengkap("Muhammad Ilham M");
        request.setAlamat("Nepal Street 142");
        request.setPekerjaan("Software Enginer");
        request.setPenghasilan("10.000.000 - 15.000.000");
        request.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        cifRepository.save(request);

        String secretKey = "413F4428472B4B6250655368566D5970337336763979244226452948404D6351";
        String jwtToken = Jwts.builder()
                .setSubject("username")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        mockMvc.perform(
                post("/api/users/cif/test")
                        .header("Authorization", "Bearer" + jwtToken)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(result -> status().isOk());

    }
}

// Use this token in your test
//        mockMvc.perform(
//
//                post("/api/users/cif/test")
//                        .header("Authorization", "Bearer" + jwtToken)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpectAll(
//                status().isOk()
//        ).andDo(result -> {
//            String responseString = result.getResponse().getContentAsString();
//            Map<String, Object> response = objectMapper.readValue(responseString, new TypeReference<Map<String, Object>>() {});
//
//            // Now you can access individual properties in the response map
//            assertEquals(200, result.getResponse().getStatus());
//            assertEquals("Token Invalid", response.get("message"));
//            assertEquals(401, response.get("status"));
//        });
