package com.digibank.restapi.service;

import com.digibank.restapi.dto.TransaksiDto;
import com.digibank.restapi.dto.TransferDto;

public interface TransferAntarBankService {

    TransaksiDto createTransferBsi(TransferDto transferDto);

}
