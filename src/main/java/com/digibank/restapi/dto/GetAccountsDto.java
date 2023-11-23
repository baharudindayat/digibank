package com.digibank.restapi.dto;

import com.digibank.restapi.model.entity.Rekening;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAccountsDto {

    private String nik;
    private String email;
    private List<Rekening> rekening;

}
