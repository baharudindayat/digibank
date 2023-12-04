package com.digibank.restapi.dto.transfer;

import com.digibank.restapi.model.enums.JenisTransaksi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransaksiDto {

        private long id;

        private Timestamp timeTransaksi;

        private String totalTransaksi;

        private String biayaAdmin;

        private JenisTransaksi JenisTransaksi;

        private String catatan;
}
