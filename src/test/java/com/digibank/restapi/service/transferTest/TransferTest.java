package com.digibank.restapi.service.transferTest;

import com.digibank.restapi.dto.transfer.TransaksiDto;
import com.digibank.restapi.dto.transfer.TransferDto;
import com.digibank.restapi.service.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
public class TransferTest {
    @Mock
    private TransferService transferService;
    @Mock
    private TransferDto transferDto;
    @Mock
    private TransaksiDto transaksiDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testTransfer() {
            when(transferService.createTransfer(transferDto))
                    .thenReturn(transaksiDto);
    }
}
