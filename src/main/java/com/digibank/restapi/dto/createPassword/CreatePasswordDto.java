package com.digibank.restapi.dto.createPassword;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreatePasswordDto {
    private String password;
    private String message;

}
