package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.login.LoginResDto;
import com.digibank.restapi.dto.login.LoginReqDto;
import com.digibank.restapi.exception.ResponseUnauthorizationException;
import com.digibank.restapi.mapper.LoginResMapper;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.repository.UserRepository;
import com.digibank.restapi.service.AuthenticationService;
import com.digibank.restapi.service.JwtService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Transactional
    @Override
    public LoginResDto login(LoginReqDto req) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        var user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new ResponseUnauthorizationException("Invalid email or password."));
        if(BCrypt.checkpw(req.getPassword(), user.getPassword())) {
            var jwt = jwtService.generateToken(user);
            var loginResDto = LoginResDto.builder().token(jwt).idUser(user.getIdUser()).build();
            return LoginResMapper.MAPPER.mapToLoginResDto(loginResDto);
        } else {
            throw new ResponseUnauthorizationException("Invalid email or password.");
        }
    }
}
