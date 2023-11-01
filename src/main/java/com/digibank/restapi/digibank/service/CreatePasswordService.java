package com.digibank.restapi.digibank.service;

import com.digibank.restapi.digibank.model.User;
import com.digibank.restapi.digibank.config.BCrypt;
import com.digibank.restapi.digibank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePasswordService {
    @Autowired
    private UserRepository userRepository;

    public String changePassword(Integer id_user, String password) {
        User user = userRepository.findById(id_user)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id_user));

        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        userRepository.save(user);

        return "Password changed successfully";
    }
}
