package com.digibank.restapi.dto;

import com.digibank.restapi.model.enums.JenisTransaksi;
import com.digibank.restapi.model.enums.TipeTransaksi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransaksiDto {

        private long id;

        private Timestamp timeTransaksi;

        private String totalTransaksi;

        private Double biayaAdmin;

        private JenisTransaksi JenisTransaksi;

        private String catatan;
}
