package com.digibank.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferDto {

    private long noRekeningTujuan;

    private long noRekeningSumber;

    private Double nominal;

    private String catatan;

    private String mpin;
}
