package com.digibank.restapi.controller;

import com.digibank.restapi.dto.Bsi.Account.RequestRekeningBsiDto;
import com.digibank.restapi.dto.Bsi.Account.ResponseRekeningBsi;
import com.digibank.restapi.dto.Bsi.Transfer.RequestTransferBsiDto;
import com.digibank.restapi.dto.Bsi.Transfer.ResponseTransferBsi;
import com.digibank.restapi.dto.TransferDto;
import com.digibank.restapi.exception.AccountNotFoundException;
import com.digibank.restapi.exception.TransferFailedException;
import com.digibank.restapi.service.TransferAntarBankService;
import com.digibank.restapi.service.TransferService;
import com.digibank.restapi.utils.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/bankbsi")
public class TransferAntarBankController {

    private TransferAntarBankService transferAntarBankService;
    private TransferService transferService;

    @PostMapping("/accountbsi")
    public ResponseEntity<?> getAccountBsi(@RequestBody RequestRekeningBsiDto rekeningBsi){
        ResponseEntity<?> response =  transferAntarBankService.getAccountRekening(rekeningBsi);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/transferbsi")
    public ResponseEntity<?> createTransferBsi(@RequestBody TransferDto transferDto){
        ResponseEntity<?> response = transferAntarBankService.createTransferBsi(transferDto);
        Object RekeningSumber = transferService.getAccountRekening(transferDto.getNoRekeningSumber());
        return ResponseHandler.generateTransferAntarBank(response, RekeningSumber);
    }

}
