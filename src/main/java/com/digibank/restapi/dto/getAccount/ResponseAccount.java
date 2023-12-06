package com.digibank.restapi.dto.getAccount;

import com.digibank.restapi.model.entity.TypeRekening;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
@Getter
@Setter
public class ResponseAccount {
    private String rekening;
    private double saldo;
    private TypeRekening typeRekening;
}
