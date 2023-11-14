package com.digibank.restapi.service;

import com.digibank.restapi.dto.response.TransactionResposneDto;
import com.digibank.restapi.dto.response.transaction.list.TransactionListResponseDto;

import java.sql.Timestamp;

public interface TransactionService {

    TransactionResposneDto getTransactionById(int id);

    TransactionListResponseDto getFilteredListTransction(boolean isDebit, boolean isKredit, Timestamp tanggalMulai, Timestamp tanggalAkhir, int page, int size);
}
