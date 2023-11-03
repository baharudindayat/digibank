package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.otp.OtpDto;
import com.digibank.restapi.exception.OtpException.OtpFailedException;
import com.digibank.restapi.mapper.UserMapper;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.repository.UserRepository;
import com.digibank.restapi.service.OtpService;
import com.digibank.restapi.utils.EmailUtil;
import com.digibank.restapi.utils.OtpUtil;
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
    public OtpDto register(OtpDto otpDto) {

        User existingUser = userRepository.findByEmail(otpDto.getEmail()).orElse(null);
        if (existingUser != null) {
            throw new OtpFailedException("Email Sudah Terdaftar");
        }

        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(otpDto.getEmail(), otp);
        } catch (MessagingException e) {
            throw new OtpFailedException("Unable to send OTP. Please try again.");
        }

        User user = UserMapper.MAPPER.mapToUser(otpDto);
        user.setOtp(otp);
        User savedOtp = userRepository.save(user);

        return UserMapper.MAPPER.mapToOtpDto(savedOtp);

    }

    @Override
    public String verifyAccount(String email, String otp) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new OtpFailedException("User not found with this email: " + email));
        if (user.getOtp().equals(otp) && Duration.between(user.getCretaedOtp(),
                LocalDateTime.now()).getSeconds() < (2 * 60)) {
            user.setActive(true);
            userRepository.save(user);
            return "OTP Terverifikasi";
        }
        throw new OtpFailedException("Maaf Kode OTP yang dimasukkan tidak valid. Silahkan coba lagi.");
    }

    @Override
    public String regenerateOtp(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new OtpFailedException("Email tidak dapat ditemukan"));
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(email, otp);
        } catch (MessagingException e) {
            throw new OtpFailedException("Tidak dapat mengirim otp, silakan coba lagi");
        }
        user.setOtp(otp);
        user.setCretaedOtp(LocalDateTime.now());
        userRepository.save(user);
        return "OTP Terkirim Kembali";
    }
}
