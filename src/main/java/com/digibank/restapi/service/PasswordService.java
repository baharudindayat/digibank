package com.digibank.restapi.service;

import com.digibank.restapi.dto.changePassword.ChangePasswordDto;
import com.digibank.restapi.dto.createPassword.CreatePasswordDto;

public interface PasswordService {
    CreatePasswordDto changePassword(Integer id_user, CreatePasswordDto request);

    CreatePasswordDto changePasswordWithValidation(Integer id_user, ChangePasswordDto changePasswordDto);
}
