package com.digibank.restapi.service;

import com.digibank.restapi.model.entity.Transaksi;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    Transaksi getTransactionById(int id);

    List<Transaksi> getListTransction(boolean isDebit, boolean isKredit, LocalDate tanggalMulai,LocalDate tanggalAkhir);
}
