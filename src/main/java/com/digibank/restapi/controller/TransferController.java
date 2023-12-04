package com.digibank.restapi.controller;

import com.digibank.restapi.dto.transfer.TransaksiDto;
import com.digibank.restapi.dto.transfer.RequestRekeningNameDto;
import com.digibank.restapi.dto.transfer.TransferDto;
import com.digibank.restapi.service.BankService;
import com.digibank.restapi.service.TransferService;
import com.digibank.restapi.utils.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/transfer")
public class TransferController {

    private TransferService transferService;
    private BankService bankService;

    @PostMapping("/digibank")
    public ResponseEntity<Object> transferDigiBank(@RequestBody TransferDto transferDto){
        TransaksiDto newTransferDto = transferService.createTransfer(transferDto);
        Object RekeningSumber = transferService.getAccountRekening(transferDto.getNoRekeningSumber());
        Object RekeningTujuan = transferService.getAccountRekening(transferDto.getNoRekeningTujuan());
        return ResponseHandler.generateResponseTransfer("Transfer Berhasil", HttpStatus.OK, newTransferDto, RekeningSumber, RekeningTujuan);
    }

    @PostMapping("/accounts")
    public ResponseEntity<Object> getAccountRekening(@RequestBody RequestRekeningNameDto id){
        Object newRekeningNameDto = transferService.getAccountRekening(id);
        return ResponseHandler.generateResponseCreate("Rekening Berhasil Ditemukan", HttpStatus.OK, newRekeningNameDto);
    }

    @GetMapping("/banks")
    public ResponseEntity<Object> getAllTransfer(){
        Object newBankDto = bankService.getAllBank();
        return ResponseHandler.generateResponseCreate("success", HttpStatus.OK, newBankDto);
    }

}
