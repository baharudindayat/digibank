package com.digibank.restapi.dto.transfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RekeningNameDto {
    private String noRekening;

    private String nama;

    private String namaBank;
}
