package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.TransaksiDto;
import com.digibank.restapi.dto.TransferDto;
import com.digibank.restapi.exception.PinFailedException;
import com.digibank.restapi.exception.TransferFailedException;
import com.digibank.restapi.model.entity.Bank;
import com.digibank.restapi.model.entity.Rekening;
import com.digibank.restapi.model.entity.Transaksi;
import com.digibank.restapi.model.enums.AccountStatus;
import com.digibank.restapi.model.enums.JenisTransaksi;
import com.digibank.restapi.model.enums.TipeTransaksi;
import com.digibank.restapi.repository.TransaksiRepository;
import com.digibank.restapi.repository.TransferRepository;
import com.digibank.restapi.repository.UserRepository;
import com.digibank.restapi.service.TransferAntarBankService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TransferAntarBankServiceImpl implements TransferAntarBankService {

    private TransferRepository transferRepository;
    private UserRepository userRepository;
    private TransaksiRepository transaksiRepository;
    @Override
    public TransaksiDto createTransferBsi(TransferDto transferDto) {
        Optional<Rekening> rekeningAsal = transferRepository.findByNoRekening(transferDto.getNoRekeningSumber());

        String mpinRekeningRequest = transferDto.getMpin();

        if (rekeningAsal.isEmpty()) {
            throw new TransferFailedException("Rekening Asal Tidak Ditemukan");
        }

        String targetUrl = "http://localhost:17000/bsi/digibank/transferbsi";
















        Integer getCountMpinWrong = rekeningAsal.get().getIdCif().getIdUsers().getCountBlockedMpin();

        AccountStatus accountStatus = rekeningAsal.get().getIdCif().getIdUsers().getStatusUser();
        if (accountStatus.equals(AccountStatus.TERBLOKIR)) {
            throw new TransferFailedException("Akun anda Sedang diblokir");
        }

        String matcherRekeningAsal = rekeningAsal.get().getIdCif().getIdUsers().getMpin();

        if (mpinRekeningRequest.equals(matcherRekeningAsal)) {

            if (transferDto.getNominal() < 10000) {
                throw new TransferFailedException("Minimal transfer Rp. 10.000");
            }

            long getTypeRekeningAsal = rekeningAsal.get().getTipeRekening().getIdTipe();
            if (getTypeRekeningAsal == 1) {
                if (transferDto.getNominal() > 5000000) {
                    throw new TransferFailedException("Maksimal transfer untuk SILVER Rp. 5.000.000");
                }
            } else if (getTypeRekeningAsal == 2) {
                if (transferDto.getNominal() > 10000000) {
                    throw new TransferFailedException("Maksimal transfer untuk GOLD Rp. 10.000.000");
                }
            } else if (getTypeRekeningAsal == 3) {
                if (transferDto.getNominal() > 15000000) {
                    throw new TransferFailedException("Maksimal transfer untuk PLATINUM Rp. 15.000.000");
                }
            }

            if (rekeningAsal.get().getSaldo() < transferDto.getNominal()) {
                throw new TransferFailedException("Saldo anda tidak mencukupi");
            }

            rekeningAsal.get().setSaldo(rekeningAsal.get().getSaldo() - transferDto.getNominal());
            transferRepository.save(rekeningAsal.get());


















            rekeningAsal.get().getIdCif().getIdUsers().setCountBlockedMpin(0);
            rekeningAsal.get().getIdCif().getIdUsers().setStatusUser(AccountStatus.ACTIVE);
            userRepository.save(rekeningAsal.get().getIdCif().getIdUsers());
        } else {
            if (getCountMpinWrong == 1){
                rekeningAsal.get().getIdCif().getIdUsers().setCountBlockedMpin(getCountMpinWrong + 1);
                userRepository.save(rekeningAsal.get().getIdCif().getIdUsers());
                throw new PinFailedException("1");
            } else if (getCountMpinWrong >= 2) {
                rekeningAsal.get().getIdCif().getIdUsers().setStatusUser(AccountStatus.TERBLOKIR);
                rekeningAsal.get().getIdCif().getIdUsers().setCountBlockedMpin(0);
                userRepository.save(rekeningAsal.get().getIdCif().getIdUsers());
                throw new TransferFailedException("Maaf! Akun ini telah terblokir karena salah memasukkan MPIN 3 kali, Silahkan hubungi call center terdekat untuk membuka blokir akun");
            } else {
                rekeningAsal.get().getIdCif().getIdUsers().setCountBlockedMpin(getCountMpinWrong + 1);
                userRepository.save(rekeningAsal.get().getIdCif().getIdUsers());
                throw new PinFailedException("2");
            }
        }

        Bank bank = new Bank();
        bank.setKodeBank(1);

        Transaksi transaksi = new Transaksi();
//        transaksi.setRekeningTujuan();
        transaksi.setRekeningAsal(rekeningAsal.get());
        transaksi.setJenisTransaksi(JenisTransaksi.PINDAHBUKU);
        transaksi.setNominal(transferDto.getNominal());
        transaksi.setCatatan(transferDto.getCatatan());
        transaksi.setTipeTransaksi(TipeTransaksi.KREDIT);
        transaksi.setBank(bank);
        transaksi.setTotalTransaksi(transferDto.getNominal());
        transaksiRepository.save(transaksi);

        TransaksiDto transaksiDto = new TransaksiDto();
        transaksiDto.setId(transaksi.getKodeTransaksi());
        transaksiDto.setTimeTransaksi(transaksi.getWaktuTransaksi());
        transaksiDto.setBiayaAdmin(String.valueOf(0.0));
        transaksiDto.setTotalTransaksi(String.format("%.0f",transaksi.getTotalTransaksi()));
        transaksiDto.setCatatan(transaksi.getCatatan());
        transaksiDto.setJenisTransaksi(transaksi.getJenisTransaksi());

        return transaksiDto;
    }


}
