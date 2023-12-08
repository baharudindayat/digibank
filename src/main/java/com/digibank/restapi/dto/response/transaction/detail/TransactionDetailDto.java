package com.digibank.restapi.dto.response.transaction.detail;

import com.digibank.restapi.model.enums.JenisTransaksi;
import com.digibank.restapi.model.enums.TipeTransaksi;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionDetailDto {
    @JsonProperty("kode_transaksi")
    private long kodeTransaksi;

    @JsonProperty("jenis_transaksi")
    private String jenisTransaksi;

    @JsonProperty("biaya_admin")
    private Integer biayaAdmin;

    @JsonProperty("total_transaksi")
    private String totalTransaksi;

    @JsonProperty("catatan")
    private String catatan;
}
