package com.digibank.restapi.service.transferTest;

import com.digibank.restapi.model.entity.Bank;
import com.digibank.restapi.service.BankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


public class GetBankTest {

    @Mock
    private BankService bankService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetBank() {
        List<Bank> banks = new ArrayList<>();
        banks.add(new Bank());
        when(bankService.getAllBank()).thenReturn(banks);
    }

}
