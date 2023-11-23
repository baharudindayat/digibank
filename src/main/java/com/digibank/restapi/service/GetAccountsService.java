package com.digibank.restapi.service;

import com.digibank.restapi.dto.GetAccountsDto;
import com.digibank.restapi.model.entity.User;

public interface GetAccountsService {

    GetAccountsDto getAccounts( String token);
}
