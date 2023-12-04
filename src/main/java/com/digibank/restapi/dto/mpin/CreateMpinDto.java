package com.digibank.restapi.dto.mpin;


import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateMpinDto {
    private String mpin;
}
