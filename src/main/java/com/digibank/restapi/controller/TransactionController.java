package com.digibank.restapi.controller;

import com.digibank.restapi.dto.response.transaction.list.TransactionListResponseDto;
import com.digibank.restapi.dto.response.TransactionResposneDto;
import com.digibank.restapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/accounts")
public class TransactionController {

    TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public ResponseEntity<TransactionListResponseDto> getListOfTransaction(
            @RequestParam(value = "isDebit", defaultValue = "true") boolean isDebit,
            @RequestParam(value = "isKredit", defaultValue = "true") boolean isKredit,
            @RequestParam(value = "tanggalMulai", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(value = "tanggalAkhir", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        TransactionListResponseDto listTransction;
        if ((startDate != null) && (endDate != null)) {
            LocalDateTime startDateLocalDateTime = startDate.atStartOfDay();
            Timestamp startDateTimeStamp = Timestamp.valueOf(startDateLocalDateTime);
            LocalDateTime endDateLocalDateTime = endDate.atTime(23, 59, 59);
            Timestamp endDateTimeStamp = Timestamp.valueOf(endDateLocalDateTime);


            listTransction = transactionService.getFilteredListTransction(
                    isDebit, isKredit, startDateTimeStamp, endDateTimeStamp, page, size
            );
        } else {
            listTransction = transactionService.getFilteredListTransction(
                    isDebit, isKredit, null, null, page, size
            );

        }

        return new ResponseEntity<>(listTransction, HttpStatus.OK);
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<TransactionResposneDto> getTransactionById(@PathVariable("id") int id) {
        TransactionResposneDto transactionResponse = transactionService.getTransactionById(id);
        return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
    }
}
