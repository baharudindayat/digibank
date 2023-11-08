package com.digibank.restapi.service.impl;

import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.repository.UserRepository;
import com.digibank.restapi.utils.EmailUtil;
import com.digibank.restapi.utils.OtpUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PasswordImpl {

    private OtpUtil otpUtil;
    private EmailUtil emailUtil;
    private UserRepository userRepository;

    public String changePassword(Integer id_user, String password) {
        User user = userRepository.findById(id_user)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id_user));

        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        userRepository.save(user);

        return "Password changed successfully";
    }

    public String changePasswordWithValidation(Integer id_user, String oldPassword, String newPassword) {
        User user = userRepository.findById(id_user)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id_user));

        // Memeriksa apakah password lama sesuai
        if (!BCrypt.checkpw(oldPassword, user.getPassword())) {
            return "Password lama tidak sesuai";
        }

        // Implementasikan validasi password baru di sini sesuai kebutuhan Anda

        // Meng-hash password baru dan menyimpannya
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userRepository.save(user);

        return "Password berhasil diubah";
    }
}
