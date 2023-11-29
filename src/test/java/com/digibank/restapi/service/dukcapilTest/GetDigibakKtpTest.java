package com.digibank.restapi.service.dukcapilTest;

import com.digibank.restapi.dto.dukcapil.DigiBankGetDto;
import com.digibank.restapi.service.impl.KtpServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class GetDigibakKtpTest {

    @Mock
    private DigiBankGetDto getDigiBankDto;

    @Mock
    private KtpServiceImpl ktpService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetKTP() {
        when(ktpService.getKtpById(getDigiBankDto.getNik()))
                .thenReturn(getDigiBankDto);
    }
}
