package com.digibank.restapi.service;

import com.digibank.restapi.dto.confirmRekening.ConfirmRekeningReqDto;
import com.digibank.restapi.dto.confirmRekening.ConfirmRekeningResDto;
import com.digibank.restapi.service.impl.ConfirmRekeningServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ConfirmRekeningServiceTest {

    @Mock
    private ConfirmRekeningReqDto mockConfirmRekeningReqDto;

    @Mock
    private ConfirmRekeningResDto mockConfirmRekeninfResDto;

    @Mock
    private ConfirmRekeningServiceImpl confirmRekeningService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConfirmRekening() {

        when(confirmRekeningService.confirmRekening(mockConfirmRekeningReqDto))
                .thenReturn(mockConfirmRekeninfResDto);

        ConfirmRekeningResDto result = confirmRekeningService.confirmRekening(mockConfirmRekeningReqDto);

        assertEquals(mockConfirmRekeninfResDto, result);

    }

}
