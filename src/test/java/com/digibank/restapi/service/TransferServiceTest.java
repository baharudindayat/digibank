package com.digibank.restapi.service;

import com.digibank.restapi.dto.transfer.*;
import com.digibank.restapi.exception.TransferFailedException;
import com.digibank.restapi.model.entity.CIF;
import com.digibank.restapi.model.entity.Rekening;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.model.enums.AccountStatus;
import com.digibank.restapi.repository.RekeningRepository;
import com.digibank.restapi.repository.TransferRepository;
import com.digibank.restapi.repository.UserRepository;
import com.digibank.restapi.service.impl.TransferServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransferServiceTest {

    @Mock
    private TransferRepository transferRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RekeningRepository rekeningRepository;

    @InjectMocks
    private TransferServiceImpl transferService;

    private TransferDto transferDto() {

        TransferDto transferDto = new TransferDto();
        transferDto.setNoRekeningSumber(Long.parseLong("7734567890"));
        transferDto.setNoRekeningTujuan(Long.parseLong("0987654321"));
        transferDto.setMpin("123456");
        transferDto.setNominal(Double.valueOf(50000));
        transferDto.setCatatan("Test Transfer");
        return transferDto;

    }

    private User user() {

        User user = new User();
        user.setIdUser(1);
        return user;

    }

    private Rekening rekening() {

        Rekening rekening = new Rekening();
        rekening.setNoRekening(Long.parseLong("7734567890"));
        rekening.setSaldo(Double.valueOf(1000000));

        CIF cif = new CIF();
        cif.setId_cif(1);

        User user = new User();
        user.setIdUser(1);

        cif.setIdUsers(user);
        rekening.setIdCif(cif);
        return rekeningRepository.save(rekening);

    }

    @Test
    void testTransferSuccess() {

        TransferDto transferDto = transferDto();
        Rekening rekeningAsal = rekening();
        Rekening rekeningTujuan = rekening();

        when(transferRepository.findByNoRekening(transferDto.getNoRekeningSumber()))
                .thenReturn(Optional.of(rekeningAsal));
        when(transferRepository.findByNoRekening(transferDto.getNoRekeningTujuan()))
                .thenReturn(Optional.of(rekeningTujuan));
        when(userRepository.save(Mockito.any()))
                .thenReturn(user());

        TransaksiDto result = transferService.createTransfer(transferDto);

        assertNotNull(result);

    }

    @Test
    void testCreateTransferWithBlockedAccount() {

        TransferDto transferDto = transferDto();
        Rekening rekeningAsal = rekening();
        rekeningAsal.getIdCif().getIdUsers().setStatusUser(AccountStatus.TERBLOKIR);
        when(transferRepository.findByNoRekening(transferDto.getNoRekeningSumber())).thenReturn(Optional.of(rekeningAsal));

        assertThrows(TransferFailedException.class, () -> transferService.createTransfer(transferDto));

    }

}
