package com.digibank.restapi.service;

import com.digibank.restapi.dto.RekeningNameDto;
import com.digibank.restapi.dto.TransaksiDto;
import com.digibank.restapi.dto.TransferDto;

public interface TransferService {
    TransaksiDto createTransfer(TransferDto transferDto);

    Object getAccountRekening(long id);

}
