package com.digibank.restapi.service;

import com.digibank.restapi.dto.deposito.DepositoReqDto;

public interface DepositoService {

    String addSaldo(DepositoReqDto depositoReqDto);
}
