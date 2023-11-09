package com.digibank.restapi.service;

import com.digibank.restapi.dto.login.LoginResDto;
import com.digibank.restapi.dto.login.LoginReqDto;

public interface AuthenticationService {

    LoginResDto login(LoginReqDto request);

}
