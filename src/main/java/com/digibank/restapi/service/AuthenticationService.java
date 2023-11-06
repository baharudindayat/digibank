package com.digibank.restapi.service;

import com.digibank.restapi.dto.login.JwtAuthenticationResponse;
import com.digibank.restapi.dto.login.LoginRequest;

public interface AuthenticationService {

    JwtAuthenticationResponse login(LoginRequest request);

}
