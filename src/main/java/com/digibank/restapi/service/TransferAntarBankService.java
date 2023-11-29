package com.digibank.restapi.service;

import com.digibank.restapi.dto.Bsi.Account.RequestRekeningBsiDto;
import com.digibank.restapi.dto.TransaksiDto;
import com.digibank.restapi.dto.TransferDto;
import org.springframework.http.ResponseEntity;

public interface TransferAntarBankService {

    ResponseEntity<?> createTransferBsi(TransferDto transferDto);

    ResponseEntity<?> getAccountRekening(RequestRekeningBsiDto requestRekeningBsiDto);

}
