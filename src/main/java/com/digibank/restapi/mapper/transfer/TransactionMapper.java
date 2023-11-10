package com.digibank.restapi.mapper.transfer;

import com.digibank.restapi.dto.response.transaction.detail.RekeningDto;
import com.digibank.restapi.dto.response.transaction.detail.TransactionDetailDto;
import com.digibank.restapi.dto.response.transaction.list.TransactionDto;
import com.digibank.restapi.model.entity.Rekening;
import com.digibank.restapi.model.entity.Transaksi;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface TransactionMapper {
    @Mapping(target = "noRekening", source = "rekening.noRekening")
    @Mapping(target = "nama", source = "rekening.idCif.namaLengkap")
    @Mapping(target = "namaBank", source = "transaksi.bank.namaBank")
    RekeningDto rekeningToRekeningDto(Rekening rekening, Transaksi transaksi);

    @Mapping(target = "kodeTransaksi", source = "kodeTransaksi")
    @Mapping(target = "tipeTransaksi", source = "tipeTransaksi")
    @Mapping(target = "biayaAdmin", expression = "java(getBiayaAdmin())")
    @Mapping(target = "totalTransaksi", expression = "java(calculateTotalTransaction(transaksi))")
    @Mapping(target = "catatan", source = "catatan")
    TransactionDetailDto transactionToTransactionDetailDto(Transaksi transaksi);

    default Double calculateTotalTransaction(Transaksi transaksi) {
        if (transaksi == null) {
            return null;
        }
        double nominal = transaksi.getNominal() != null ? transaksi.getNominal() : 0.0;
        double biayaAdmin = 6500.0; // Biaya admin yang tetap
        return nominal + biayaAdmin;
    }

    default Integer getBiayaAdmin() {
        return 6500;
    }

    @Mapping(target = "kodeTransaksi", source = "transaction.kodeTransaksi")
    @Mapping(target = "tipeTransaksi", source = "transaction.tipeTransaksi")
    @Mapping(target = "nama", source = "transaction.rekeningTujuan.idCif.namaLengkap")
    @Mapping(target = "jumlahTransaksi", source = "transaction.totalTransaksi")
    @Mapping(target = "tanggal", source = "transaction.waktuTransaksi")
    TransactionDto transactionToTransactionDto(Transaksi transaction);

    List<TransactionDto> transactionToTransactionListDto(List<Transaksi> transactions);
}
