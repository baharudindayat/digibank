package com.digibank.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class TypeRekenignDto {
    private Long idTipe;
    private String namaTipe;
    private String limitTransfer;
}
