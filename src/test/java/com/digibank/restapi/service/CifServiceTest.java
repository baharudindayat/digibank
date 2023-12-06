package com.digibank.restapi.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

public class CifServiceTest {

    @Test
    public void testCreateCif() {

        long idUser = 1L;
        long idTipe = 2L;

        CifService cifServiceMock = mock(CifService.class);

        when(cifServiceMock.createCif(any(), anyLong(), anyLong())).thenReturn("OK");

        String result = cifServiceMock.createCif(null, idUser, idTipe);

        assertEquals("OK", result);

        verify(cifServiceMock).createCif(any(), anyLong(), anyLong());
    }
}
