package com.digibank.restapi.service.dukcapilTest;

import com.digibank.restapi.dto.dukcapil.KtpDto;
import com.digibank.restapi.service.impl.KtpServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;


public class CreateKtpTest {

    @Mock
    private KtpDto KTPDto;

    @Mock
    private KtpServiceImpl ktpService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertKTP() {
        when(ktpService.createKtp(KTPDto))
                .thenReturn(KTPDto);
    }
}
