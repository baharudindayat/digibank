package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.response.TransactionResposneDto;
import com.digibank.restapi.dto.response.transaction.detail.RekeningDto;
import com.digibank.restapi.dto.response.transaction.list.TransactionDto;
import com.digibank.restapi.dto.response.transaction.list.TransactionListResponseDto;
import com.digibank.restapi.exception.TransactionNotFoundException;
import com.digibank.restapi.mapper.transfer.TransactionMapper;
import com.digibank.restapi.model.entity.Rekening;
import com.digibank.restapi.model.entity.Transaksi;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.model.enums.TipeTransaksi;
import com.digibank.restapi.repository.TransactionsRepository;
import com.digibank.restapi.repository.UserRepository;
import com.digibank.restapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionsRepository transactionsRepository;
    private final TransactionMapper transactionMapper;

    private final UserRepository userRepository;

    @Autowired
    public TransactionServiceImpl(TransactionsRepository transactionsRepository, TransactionMapper transactionMapper, UserRepository userRepository) {
        this.transactionsRepository = transactionsRepository;
        this.transactionMapper = transactionMapper;
        this.userRepository = userRepository;
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

            if(successfullTransaction.getTipeTransaksi() == TipeTransaksi.KREDIT){
                return TransactionResposneDto.builder()
                        .error(false)
                        .message("transaksi ditemukan")
                        .pengirim(rekeningTujuanDto)
                        .penerima(rekeningAsalDto)
                        .data(transactionMapper.transactionToTransactionDetailDto(successfullTransaction)).build();
            }else {
                return TransactionResposneDto.builder()
                        .error(false)
                        .message("transaksi ditemukan")
                        .pengirim(rekeningAsalDto)
                        .penerima(rekeningTujuanDto)
                        .data(transactionMapper.transactionToTransactionDetailDto(successfullTransaction)).build();
            }

        } else {
            throw new TransactionNotFoundException("Transaction not found");
        }
    }

    @Override
    public TransactionListResponseDto getFilteredListTransction(String email,boolean isDebit, boolean isKredit, Timestamp tanggalMulai, Timestamp tanggalAkhir, int pageNumber, int size) {
        Pageable pageable = PageRequest.of(((pageNumber > 0) ? pageNumber - 1 : pageNumber), size, Sort.by(Sort.Direction.ASC, "waktuTransaksi"));
        Page<Transaksi> filteredTransactions = null;

        Optional<User> opUser = userRepository.findByEmail(email);

        User user;
        List<Rekening> rekenings;
        if(opUser.isPresent()){
            user = opUser.get();
            rekenings = user.getCif().getRekeningList();
        }else{
            throw new TransactionNotFoundException("wrong email");
        }

        TipeTransaksi tipeTransaksi;
        if ((tanggalMulai == null) && (tanggalAkhir == null)) {
            if (isDebit && isKredit) {
                filteredTransactions = transactionsRepository.findAllByRekeningAsalIn(rekenings, pageable);
            } else if (isDebit) {
                tipeTransaksi = TipeTransaksi.DEBIT;
                filteredTransactions = transactionsRepository.findByTipeTransaksiAndRekeningAsalInOrRekeningTujuanIn(tipeTransaksi,rekenings,pageable);
            } else if (isKredit) {
                tipeTransaksi = TipeTransaksi.KREDIT;
                filteredTransactions = transactionsRepository.findByTipeTransaksiAndRekeningAsalInOrRekeningTujuanIn(tipeTransaksi,rekenings, pageable);
            }
        } else if ((tanggalMulai != null) && (tanggalAkhir != null)) {
            if (isDebit && isKredit) {
                filteredTransactions = transactionsRepository.findByWaktuTransaksiBetweenAndRekeningAsalInOrRekeningTujuanIn(tanggalMulai, tanggalAkhir,rekenings, pageable);
            } else if (isDebit) {
                tipeTransaksi = TipeTransaksi.DEBIT;
                filteredTransactions = transactionsRepository.findByTipeTransaksiAndWaktuTransaksiBetweenAndRekeningAsalInOrRekeningTujuanIn(tipeTransaksi, tanggalMulai, tanggalAkhir,rekenings, pageable);
            } else if (isKredit) {
                tipeTransaksi = TipeTransaksi.KREDIT;
                filteredTransactions = transactionsRepository.findByTipeTransaksiAndWaktuTransaksiBetweenAndRekeningAsalInOrRekeningTujuanIn(tipeTransaksi, tanggalMulai, tanggalAkhir,rekenings, pageable);
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
