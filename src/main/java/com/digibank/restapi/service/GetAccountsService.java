package com.digibank.restapi.service;

import com.digibank.restapi.dto.getAccount.GetAccountsDto;

public interface GetAccountsService {

    GetAccountsDto getAccounts( String token);
}
