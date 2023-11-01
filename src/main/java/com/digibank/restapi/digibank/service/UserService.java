package com.digibank.restapi.digibank.service;

import com.digibank.restapi.digibank.dto.RegisterDto;
import com.digibank.restapi.digibank.entity.User;
import com.digibank.restapi.digibank.repository.UserRepository;
import com.digibank.restapi.digibank.util.EmailUtil;
import com.digibank.restapi.digibank.util.OtpUtil;
import jakarta.mail.MessagingException;
import java.time.Duration;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private UserRepository userRepository;

    public String register(RegisterDto registerDto) {
        // Check if the email is already registered
        User existingUser = userRepository.findByEmail(registerDto.getEmail()).orElse(null);
        if (existingUser != null) {
            // Handle the case where the email already exists
            return "Email Sudah Terdaftar";
        }

        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(registerDto.getEmail(), otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send OTP. Please try again.");
        }
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setOtp(otp);
        user.setCreated_otp(LocalDateTime.now());
        userRepository.save(user);
        return "success";
    }

    public String verifyAccount(String email, String otp) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        if (user.getOtp().equals(otp) && Duration.between(user.getCreated_otp(),
                LocalDateTime.now()).getSeconds() < (2 * 60)) {
            user.setActive(true);
            userRepository.save(user);
            return "OTP Terverifikasi";
        }
        return "Maaf Kode OTP yang dimasukkan tidak valid. Silahkan coba lagi.";
    }

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
        user.setCreated_otp(LocalDateTime.now());
        userRepository.save(user);
        return "OTP Terkirim Kembali";
    }

    public String changePassword(Integer id_user, String password) {
        User user = userRepository.findById(id_user)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id_user));

        // Implement password validation and hashing logic as needed


        user.setPassword(password);
        userRepository.save(user);

        return "Password changed successfully";
    }

}

