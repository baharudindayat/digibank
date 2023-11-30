package com.digibank.restapi.service;

import com.digibank.restapi.dto.changePassword.ChangePasswordDto;
import com.digibank.restapi.dto.createPassword.CreatePasswordDto;

public interface PasswordService {
    CreatePasswordDto createPassword(Long id_user, CreatePasswordDto request);

    CreatePasswordDto changePasswordWithValidation(String token, ChangePasswordDto changePasswordDto);
}
