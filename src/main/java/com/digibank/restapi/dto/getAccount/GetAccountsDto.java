package com.digibank.restapi.dto.getAccount;

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

    private String name;
    private String nik;
    private String email;
    private List<ResponseAccount> accounts;

}
