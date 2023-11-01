package com.digibank.restapi.digibank.service;

import com.digibank.restapi.digibank.config.BCrypt;
import com.digibank.restapi.digibank.model.User;
import com.digibank.restapi.digibank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordService {
    @Autowired
    private UserRepository userRepository;

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
