package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.otp.OtpDto;
import com.digibank.restapi.exception.OtpException.OtpResponse;
import com.digibank.restapi.mapper.UserMapper;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.repository.UserRepository;
import com.digibank.restapi.service.OtpService;
import com.digibank.restapi.utils.EmailUtil;
import com.digibank.restapi.utils.OtpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private UserRepository userRepository;

    @Override
    public String register(OtpDto otpDto) {
        OtpResponse response = new OtpResponse();

        // Check if the email is already registered
        User existingUser = userRepository.findByEmail(otpDto.getEmail()).orElse(null);
        if (existingUser != null) {
            response.setStatus(400);
            response.setMessage("Email Sudah Terdaftar");
        } else {
            String otp = otpUtil.generateOtp();
            try {
                emailUtil.sendOtpEmail(otpDto.getEmail(), otp);
            } catch (MessagingException e) {
                throw new RuntimeException("Unable to send OTP. Please try again.");
            }
            User user = UserMapper.INSTANCE.otpDtoToUser(otpDto);
            user.setOtp(otp);
            userRepository.save(user);

            response.setStatus(200);
            response.setId_user(String.valueOf(user.getId_user()));
            response.setEmail(user.getEmail());
            response.setOtp(user.getOtp());
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to process response.");
        }
    }

    @Override
    public String verifyAccount(String email, String otp) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        if (user.getOtp().equals(otp) && Duration.between(user.getCretaedOtp(),
                LocalDateTime.now()).getSeconds() < (2 * 60)) {
            user.setActive(true);
            userRepository.save(user);
            return "OTP Terverifikasi";
        }
        return "Maaf Kode OTP yang dimasukkan tidak valid. Silahkan coba lagi.";
    }

    @Override
    public String regenerateOtp(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email tidak dapat ditemukan"));
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Tidak dapat mengirim otp, silakan coba lagi");
        }
        user.setOtp(otp);
        user.setCretaedOtp(LocalDateTime.now());
        userRepository.save(user);
        return "OTP Terkirim Kembali";
    }
}