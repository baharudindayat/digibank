package com.digibank.restapi.mapper.transfer;

import com.digibank.restapi.dto.response.transaction.detail.RekeningDto;
import com.digibank.restapi.dto.response.transaction.detail.TransactionDetailDto;
import com.digibank.restapi.dto.response.transaction.list.TransactionDto;
import com.digibank.restapi.model.entity.Rekening;
import com.digibank.restapi.model.entity.Transaksi;
import com.digibank.restapi.model.enums.JenisTransaksi;
import com.digibank.restapi.model.enums.TipeTransaksi;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
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
    @Mapping(target = "jenisTransaksi", expression = "java(getJenisTransakasi(transaksi))")
    @Mapping(target = "biayaAdmin", expression = "java(getBiayaAdmin(transaksi))")
    @Mapping(target = "totalTransaksi", expression = "java(calculateTotalTransaction(transaksi))")
    @Mapping(target = "catatan", source = "catatan")
    TransactionDetailDto transactionToTransactionDetailDto(Transaksi transaksi);

    default String getJenisTransakasi(Transaksi transaksi){
        if(transaksi.getJenisTransaksi() == JenisTransaksi.ANTARREKENING){
            return "Antar Rekening";
        }
        return "Antar Bank";
    }

    default String calculateTotalTransaction(Transaksi transaksi) {
        if (transaksi == null) {
            return null;
        }
        double nominal = transaksi.getNominal() != null ? transaksi.getNominal() : 0.0;
        double biayaAdmin = getBiayaAdmin(transaksi);
        double totalTransaction =  nominal + biayaAdmin;
        return new BigDecimal(totalTransaction).toPlainString();
    }

    default Integer getBiayaAdmin(Transaksi transaksi) {
        if(transaksi.getJenisTransaksi() == JenisTransaksi.ANTARREKENING) {
            return 0;
        }
        return 6500;
    }

    default String getTotalTransaction(double totalTransaction){
        return new BigDecimal(totalTransaction).toPlainString();
    }

    @Mapping(target = "kodeTransaksi", source = "transaction.kodeTransaksi")
    @Mapping(target = "tipeTransaksi", source = "transaction.tipeTransaksi")
    @Mapping(target = "nama", expression = "java(getRekeningByTransactionType(transaction))")
    @Mapping(target = "jumlahTransaksi", expression = "java(getTotalTransaction(transaction.getTotalTransaksi()))")
    @Mapping(target = "tanggal", source = "transaction.waktuTransaksi")
    TransactionDto transactionToTransactionDto(Transaksi transaction);

    default String getRekeningByTransactionType(Transaksi transaksi) {
        if(transaksi.getTipeTransaksi() == TipeTransaksi.DEBIT){
            return transaksi.getRekeningAsal().getIdCif().getNamaLengkap();
        }
        return transaksi.getRekeningTujuan().getIdCif().getNamaLengkap();
    }

    List<TransactionDto> transactionToTransactionListDto(List<Transaksi> transactions);
}
