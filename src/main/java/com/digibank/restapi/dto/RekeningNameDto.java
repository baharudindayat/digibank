package com.digibank.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RekeningNameDto {
    private long noRekening;

    private String nama;

    private String namaBank;
}
