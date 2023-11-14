package com.digibank.restapi.dto.response.transaction.list;

import com.digibank.restapi.model.enums.TipeTransaksi;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class TransactionDto {
    private int kodeTransaksi;
    private TipeTransaksi tipeTransaksi;
    private String nama;
    private Double jumlahTransaksi;
    private Timestamp tanggal;
}
