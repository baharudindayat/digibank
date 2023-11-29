package com.digibank.restapi.controller;

import com.digibank.restapi.dto.Bsi.Account.RequestRekeningBsiDto;
import com.digibank.restapi.dto.Bsi.Account.ResponseRekeningBsi;
import com.digibank.restapi.dto.Bsi.Transfer.RequestTransferBsiDto;
import com.digibank.restapi.dto.Bsi.Transfer.ResponseTransferBsi;
import com.digibank.restapi.dto.TransferDto;
import com.digibank.restapi.exception.AccountNotFoundException;
import com.digibank.restapi.exception.TransferFailedException;
import com.digibank.restapi.service.TransferAntarBankService;
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

    @PostMapping("/accountbsi")
    public ResponseEntity<?> getAccountBsi(@RequestBody RequestRekeningBsiDto rekeningBsi){
        String targetUrl = "http://localhost:17000/bsi/digibank/accountbsi";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<RequestRekeningBsiDto> request = new HttpEntity<>(rekeningBsi, headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<ResponseRekeningBsi> response = restTemplate.postForEntity(targetUrl, request, ResponseRekeningBsi.class);

            return ResponseEntity.ok(response.getBody());

        } catch (HttpClientErrorException.NotFound notFoundException) {
            throw new AccountNotFoundException("Account bsi Not Found");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PostMapping("/transferbsii")
    public ResponseEntity<?> createTransferBsi1(@RequestBody RequestTransferBsiDto transferBsiDto){

        String targetUrl = "http://localhost:17000/bsi/digibank/transferbsi";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<RequestTransferBsiDto> request = new HttpEntity<>(transferBsiDto, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ResponseTransferBsi> response = restTemplate.postForEntity(targetUrl, request, ResponseTransferBsi.class);
            return ResponseEntity.ok(response.getBody());

        }catch (HttpClientErrorException.BadRequest badRequestException){
            throw new TransferFailedException("Transfer Failed");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }

    }

    @PostMapping("/transferbsi")
    public ResponseEntity<?> createTransferBsi(@RequestBody TransferDto transferDto){
            try {
                return ResponseEntity.ok(transferAntarBankService.createTransferBsi(transferDto));
            }catch (HttpClientErrorException.BadRequest badRequestException){
                throw new TransferFailedException("Transfer Failed");
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
            }
    }

}
