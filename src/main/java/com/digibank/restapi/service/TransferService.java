package com.digibank.restapi.service;

import com.digibank.restapi.dto.transfer.TransaksiDto;
import com.digibank.restapi.dto.transfer.RequestRekeningNameDto;
import com.digibank.restapi.dto.transfer.TransferDto;

public interface TransferService {
    TransaksiDto createTransfer(TransferDto transferDto);

    Object getAccountRekening(RequestRekeningNameDto requestRekeningNameDto);

    Object getAccountRekening(long noRekening);
}
