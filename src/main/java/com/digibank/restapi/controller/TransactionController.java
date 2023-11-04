package com.digibank.restapi.controller;

import com.digibank.restapi.model.entity.Transaksi;
import com.digibank.restapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/accounts")
public class TransactionController {

    TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaksi>> getListOfTransaction(
            @RequestParam(value = "debit") boolean isDebit,
            @RequestParam(value = "kredit") boolean isKredit,
            @RequestParam(value = "tanggal mulai", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("tanggal akhir") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        List<Transaksi> listTransction = transactionService.getListTransction(
                isDebit, isKredit, startDate, endDate
        );
        return new ResponseEntity<>(listTransction, HttpStatus.OK);
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transaksi> getTransactionById(@PathVariable("id") int id) {
        Transaksi transaction = transactionService.getTransactionById(id);
        return new ResponseEntity<>(transaction,HttpStatus.OK);
    }
}
