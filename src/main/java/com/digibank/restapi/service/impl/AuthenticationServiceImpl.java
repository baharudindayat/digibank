package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.login.LoginReqDto;
import com.digibank.restapi.dto.login.LoginResDto;
import com.digibank.restapi.exception.ResponseBadRequestException;
import com.digibank.restapi.exception.ResponseUnauthorizationException;
import com.digibank.restapi.mapper.LoginResMapper;
import com.digibank.restapi.model.enums.AccountStatus;
import com.digibank.restapi.repository.UserRepository;
import com.digibank.restapi.service.AuthenticationService;
import com.digibank.restapi.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Transactional
    @Override
    public LoginResDto login(LoginReqDto req) {
        var user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new ResponseBadRequestException("Maaf! Email atau kata sandi yang dimasukkan salah. Pastikan email dan kata sandi benar."));

        if(user.getStatusUser() == AccountStatus.TERBLOKIR) {
            throw new ResponseBadRequestException("Maaf! Akun terblokir. Silakan hubungi cabang bank terdekat.");
        }
        if(user.getStatusUser() == AccountStatus.INACTIVE) {
            throw new ResponseBadRequestException("Maaf! Akun Anda belum aktif. Silahkan aktifkan akun terlebih dahulu.");
        }
        if(BCrypt.checkpw(req.getPassword(), user.getPassword()) && user.getStatusUser() == AccountStatus.ACTIVE) {
            var jwt = jwtService.generateToken(user);
            return LoginResDto.builder().token(jwt).build();
        } else {
            throw new ResponseBadRequestException("Maaf! Email atau kata sandi yang dimasukkan salah. Pastikan email dan kata sandi benar.");
        }
    }
}
