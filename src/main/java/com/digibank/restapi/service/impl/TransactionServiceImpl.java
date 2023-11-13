package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.response.TransactionResposneDto;
import com.digibank.restapi.dto.response.transaction.detail.RekeningDto;
import com.digibank.restapi.dto.response.transaction.list.TransactionDto;
import com.digibank.restapi.dto.response.transaction.list.TransactionListResponseDto;
import com.digibank.restapi.exception.TransactionNotFoundException;
import com.digibank.restapi.mapper.transfer.TransactionMapper;
import com.digibank.restapi.model.entity.Rekening;
import com.digibank.restapi.model.entity.Transaksi;
import com.digibank.restapi.model.enums.TipeTransaksi;
import com.digibank.restapi.repository.TransactionsRepository;
import com.digibank.restapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionsRepository transactionsRepository;
    private final TransactionMapper transactionMapper;


    @Autowired
    public TransactionServiceImpl(TransactionsRepository transactionsRepository, TransactionMapper transactionMapper) {
        this.transactionsRepository = transactionsRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionResposneDto getTransactionById(int id) throws TransactionNotFoundException {
        var transaction = transactionsRepository.findById((long) id);
        Rekening rekeningAsal;
        Rekening rekeningTujuan;

        if (transaction.isPresent()) {
            Transaksi successfullTransaction = transaction.get();
            rekeningAsal = successfullTransaction.getRekeningAsal();
            rekeningTujuan = successfullTransaction.getRekeningTujuan();

            RekeningDto rekeningAsalDto = transactionMapper.rekeningToRekeningDto(rekeningAsal, successfullTransaction);
            RekeningDto rekeningTujuanDto = transactionMapper.rekeningToRekeningDto(rekeningTujuan, successfullTransaction);

            return TransactionResposneDto.builder()
                    .error(false)
                    .message("transaksi ditemukan")
                    .pengirim(rekeningAsalDto)
                    .penerima(rekeningTujuanDto)
                    .data(transactionMapper.transactionToTransactionDetailDto(successfullTransaction)).build();
        } else {
            throw new TransactionNotFoundException("Transaction not found");
        }
    }

    @Override
    public TransactionListResponseDto getFilteredListTransction(boolean isDebit, boolean isKredit, Timestamp tanggalMulai, Timestamp tanggalAkhir, int pageNumber, int size) {
        Pageable pageable = PageRequest.of(((pageNumber > 0) ? pageNumber - 1 : pageNumber), size, Sort.by(Sort.Direction.ASC, "waktuTransaksi"));
        Page<Transaksi> filteredTransactions = null;

        TipeTransaksi tipeTransaksi;
        if ((tanggalMulai == null) && (tanggalAkhir == null)) {
            if (isDebit && isKredit) {
                filteredTransactions = transactionsRepository.findAll(pageable);
            } else if (isDebit) {
                tipeTransaksi = TipeTransaksi.DEBIT;
                filteredTransactions = transactionsRepository.findAllByTipeTransaksi(tipeTransaksi, pageable);
            } else if (isKredit) {
                tipeTransaksi = TipeTransaksi.KREDIT;
                filteredTransactions = transactionsRepository.findAllByTipeTransaksi(tipeTransaksi, pageable);
            }
        } else if ((tanggalMulai != null) && (tanggalAkhir != null)) {
            if (isDebit && isKredit) {
                filteredTransactions = transactionsRepository.findAllByWaktuTransaksiBetween(tanggalMulai, tanggalAkhir, pageable);
            } else if (isDebit) {
                tipeTransaksi = TipeTransaksi.DEBIT;
                filteredTransactions = transactionsRepository.findAllByTipeTransaksiAndWaktuTransaksiBetween(tipeTransaksi, tanggalMulai, tanggalAkhir, pageable);
            } else if (isKredit) {
                tipeTransaksi = TipeTransaksi.KREDIT;
                filteredTransactions = transactionsRepository.findAllByTipeTransaksiAndWaktuTransaksiBetween(tipeTransaksi, tanggalMulai, tanggalAkhir, pageable);
            }
        }

        if (filteredTransactions == null) {
            throw new TransactionNotFoundException("Transaction not found");
        }

        List<TransactionDto> transactionList = transactionMapper.transactionToTransactionListDto(filteredTransactions.getContent());

        TransactionListResponseDto transactionListResponse = TransactionListResponseDto.builder()
                .error(false)
                .message("success")
                .transactions(transactionList)
                .build();

        return transactionListResponse;
    }
}
