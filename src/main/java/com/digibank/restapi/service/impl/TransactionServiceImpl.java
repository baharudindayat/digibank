package com.digibank.restapi.service.impl;

import com.digibank.restapi.exception.TransactionNotFoundException;
import com.digibank.restapi.model.entity.Transaksi;
import com.digibank.restapi.repository.TransactionsRepository;
import com.digibank.restapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionsRepository transactionsRepository;

    @Autowired
    public TransactionServiceImpl(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    @Override
    public Transaksi getTransactionById(int id) {
        var transaction = transactionsRepository.findById((long) id);
        if(transaction.isPresent()) {
            return transaction.get();
        }else {
            throw new TransactionNotFoundException("transaction not found");
        }
    }

    @Override
    public List<Transaksi> getListTransction(boolean isDebit, boolean isKredit, LocalDate tanggalMulai, LocalDate tanggalAkhir) {
        return transactionsRepository.findFilteredTransactions(isDebit,isKredit,tanggalMulai,tanggalAkhir);
    }
}
