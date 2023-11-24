package com.digibank.restapi.dto;


import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateMpinDto {
    private String mpin;
}
