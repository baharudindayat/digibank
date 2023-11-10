//package com.digibank.restapi.service;
//
//import com.digibank.restapi.dto.UsersDto;
//import com.digibank.restapi.model.entity.User;
//import com.digibank.restapi.repository.UserRepository;
//import com.digibank.restapi.utils.EmailUtil;
//import com.digibank.restapi.utils.OtpUtil;
//import jakarta.mail.MessagingException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//import java.time.LocalDateTime;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private OtpUtil otpUtil;
//    @Autowired
//    private EmailUtil emailUtil;
//    @Autowired
//    private UserRepository userRepository;
//
//    public String register(UsersDto registerDto) {
//        // Check if the email is already registered
//        User existingUser = userRepository.findByEmail(registerDto.getEmail()).orElse(null);
//        if (existingUser != null) {
//            // Handle the case where the email already exists
//            return "Email Sudah Terdaftar";
//        }
//
//        String otp = otpUtil.generateOtp();
//        try {
//            emailUtil.sendOtpEmail(registerDto.getEmail(), otp);
//        } catch (MessagingException e) {
//            throw new RuntimeException("Unable to send OTP. Please try again.");
//        }
//        User user = new User();
//        user.setEmail(registerDto.getEmail());
//        user.setOtp(otp);
//        user.setCretaedOtp(LocalDateTime.now());
//        userRepository.save(user);
//        return "success";
//    }
//
//    public String verifyAccount(String email, String otp) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
//        if (user.getOtp().equals(otp) && Duration.between(user.getCretaedOtp(),
//                LocalDateTime.now()).getSeconds() < (2 * 60)) {
//            user.setActive(true);
//            userRepository.save(user);
//            return "OTP Terverifikasi";
//        }
//        return "Maaf Kode OTP yang dimasukkan tidak valid. Silahkan coba lagi.";
//    }
//
//    public String regenerateOtp(String email) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("Email tidak dapat ditemukan"));
//        String otp = otpUtil.generateOtp();
//        try {
//            emailUtil.sendOtpEmail(email, otp);
//        } catch (MessagingException e) {
//            throw new RuntimeException("Tidak dapat mengirim otp, silakan coba lagi");
//        }
//        user.setOtp(otp);
//        user.setCretaedOtp(LocalDateTime.now());
//        userRepository.save(user);
//        return "OTP Terkirim Kembali";
//    }
//
//    public String changePassword(Integer id_user, String password) {
//        User user = userRepository.findById(id_user)
//                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id_user));
//
//        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
//        userRepository.save(user);
//
//        return "Password changed successfully";
//    }
//
//    public String changePasswordWithValidation(Integer id_user, String oldPassword, String newPassword) {
//        User user = userRepository.findById(id_user)
//                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id_user));
//
//        // Memeriksa apakah password lama sesuai
//        if (!BCrypt.checkpw(oldPassword, user.getPassword())) {
//            return "Password lama tidak sesuai";
//        }
//
//        // Implementasikan validasi password baru di sini sesuai kebutuhan Anda
//
//        // Meng-hash password baru dan menyimpannya
//        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
//        user.setPassword(hashedPassword);
//        userRepository.save(user);
//
//        return "Password berhasil diubah";
//    }
//
//}
//
