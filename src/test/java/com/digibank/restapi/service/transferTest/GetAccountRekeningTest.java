package com.digibank.restapi.service.transferTest;

import com.digibank.restapi.dto.transfer.RekeningNameDto;
import com.digibank.restapi.dto.transfer.RequestRekeningNameDto;
import com.digibank.restapi.service.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;


public class GetAccountRekeningTest {

    @Mock
    private RequestRekeningNameDto requestRekeningNameDto;
    @Mock
    private RekeningNameDto rekeningNameDto;

    @Mock
    private TransferService transferService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAccountRekening() {
        when(transferService.getAccountRekening(requestRekeningNameDto))
                .thenReturn(rekeningNameDto);
    }

}
