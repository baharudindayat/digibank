package com.digibank.restapi.utils.db;

import com.digibank.restapi.model.Ktp;
import com.digibank.restapi.model.entity.*;
import com.digibank.restapi.model.enums.AccountStatus;
import com.digibank.restapi.model.enums.JenisTransaksi;
import com.digibank.restapi.model.enums.TipeTransaksi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class PrepopulateDatabase implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void run(String... args)  {
        //ktp
        Ktp ktp = new Ktp();
        ktp.setNik("3324062206020003");
        ktp.setProvinsi("Jawa Barat");
        ktp.setKota("Bandung");
        ktp.setNama("John Doe");
        ktp.setTempat_lahir("Bandung");
        ktp.setTanggal_lahir(new Date(1990, 1, 1));
        ktp.setJenis_kelamin("Laki-laki");
        ktp.setGolongan_darah("O");
        ktp.setAlamat("Jl. Sudirman No. 1");
        ktp.setRt("001");
        ktp.setRw("001");
        ktp.setKelurahan("Sukajadi");
        ktp.setKecamatan("Sukajadi");
        ktp.setAgama("Islam");
        ktp.setStatus_perkawinan("Belum Kawin");
        ktp.setPekerjaan("Karyawan");
        ktp.setKewarganegaraan("Indonesia");
        ktp.setBerlaku_hingga("Seumur Hidup");
        ktp.setTanggal_perekaman(new Date(2023, 10, 30));

        entityManager.persist(ktp);

        //bank
        Bank digibank = new Bank();
        digibank.setNamaBank("Digibank");
        entityManager.persist(digibank);

        Bank bsi = new Bank();
        bsi.setNamaBank("Bank Syariah Indonesia");
        entityManager.persist(bsi);

        //userBank
        User userBank = new User();
        userBank.setEmail("Digibank.digi.co.id");
        userBank.setPassword("$2y$10$KMII6gfpriF5nawIrKFiIerrafGi4oTbFI4b9ZzXBjn26PBdZS.7O");
        userBank.setStatusUser(AccountStatus.ACTIVE);
        userBank.setMpin("898725");
        userBank.setActive(true);
        userBank.setCountBlockedMpin(0);
        entityManager.persist(userBank);


        //user
        User userDevano = new User();
        userDevano.setEmail("devanozaidan@gmail.com");
        userDevano.setPassword("$2y$10$KMII6gfpriF5nawIrKFiIerrafGi4oTbFI4b9ZzXBjn26PBdZS.7O");
        userDevano.setStatusUser(AccountStatus.ACTIVE);
        userDevano.setMpin("898725");
        userDevano.setActive(true);
        userDevano.setCountBlockedMpin(0);
        entityManager.persist(userDevano);

        User userKepin = new User();
        userKepin.setEmail("kevin@gmail.com");
        userKepin.setPassword("$2y$10$KMII6gfpriF5nawIrKFiIerrafGi4oTbFI4b9ZzXBjn26PBdZS.7O");
        userKepin.setStatusUser(AccountStatus.ACTIVE);
        userKepin.setMpin("898725");
        userKepin.setActive(true);
        userKepin.setCountBlockedMpin(0);
        entityManager.persist(userKepin);

        //CIFBank
        CIF cifBank = new CIF();
        cifBank.setNik("1231231231312314");
        cifBank.setNamaLengkap("Digibank");
        cifBank.setAlamat("example address");
        cifBank.setPekerjaan("example job");
        cifBank.setPenghasilan("2000000000000000000000");
        cifBank.setIdUsers(userBank);
        entityManager.persist(cifBank);



        //CIF
        CIF cifDevano = new CIF();
        cifDevano.setNik("1231231231312313");
        cifDevano.setNamaLengkap("Muhammad Devano Zaidan");
        cifDevano.setAlamat("example address");
        cifDevano.setPekerjaan("example job");
        cifDevano.setPenghasilan("2000000");
        cifDevano.setIdUsers(userDevano);
        entityManager.persist(cifDevano);

        CIF cifKepin = new CIF();
        cifKepin.setNik("1231231231312315");
        cifKepin.setNamaLengkap("Kevin raihan saleh");
        cifKepin.setAlamat("example address");
        cifKepin.setPekerjaan("example job");
        cifKepin.setPenghasilan("2000000");
        cifKepin.setIdUsers(userKepin);
        entityManager.persist(cifKepin);

        //tipe rekening
        TypeRekening tipeRekeningGold = new TypeRekening();
        tipeRekeningGold.setNamaTipe("GOLD");
        tipeRekeningGold.setLimitTransfer("500000000");
        entityManager.persist(tipeRekeningGold);

        TypeRekening tipeRekeningSilver = new TypeRekening();
        tipeRekeningSilver.setNamaTipe("SILVER");
        tipeRekeningSilver.setLimitTransfer("1000000000");
        entityManager.persist(tipeRekeningSilver);

        TypeRekening tipeRekeningPlatinum = new TypeRekening();
        tipeRekeningPlatinum.setNamaTipe("PLATINUM");
        tipeRekeningPlatinum.setLimitTransfer("1500000000");
        entityManager.persist(tipeRekeningPlatinum);

        //rekeningBank
        Rekening rekeningBank = new Rekening();
        rekeningBank.setNoRekening(1234567890123456789L);
        rekeningBank.setSaldo(0.0);
        rekeningBank.setTipeRekening(tipeRekeningPlatinum);
        rekeningBank.setIdCif(cifBank);
        entityManager.persist(rekeningBank);
        

        //rekening
        Rekening devano = new Rekening();
        devano.setNoRekening(7727272726678L);
        devano.setSaldo(3000000.0);
        devano.setTipeRekening(tipeRekeningGold);
        devano.setIdCif(cifDevano);
        entityManager.persist(devano);


        Rekening kepin = new Rekening();
        kepin.setNoRekening(7727272726677L);
        kepin.setSaldo(2000000.0);
        kepin.setTipeRekening(tipeRekeningGold);
        kepin.setIdCif(cifKepin);
        entityManager.persist(kepin);

        //transaksi
        boolean isDebit = true;
//        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Transaksi exampleTransaction = new Transaksi();
            exampleTransaction.setNominal(230000000.0 + i);
            exampleTransaction.setRekeningAsal(devano);
            exampleTransaction.setRekeningTujuan(kepin);
            exampleTransaction.setCatatan("sedekah");
            exampleTransaction.setBank(bsi);
            long offset = Timestamp.valueOf("2023-10-01 00:00:00").getTime();
            long end = Timestamp.valueOf("2023-12-31 23:59:59").getTime();
            long diff = end - offset + 1;
            long randomTime = offset + (long) (Math.random() * diff);
            exampleTransaction.setWaktuTransaksi(new Timestamp(randomTime));
            exampleTransaction.setJenisTransaksi(JenisTransaksi.PINDAHBUKU);
            exampleTransaction.setTotalTransaksi(exampleTransaction.getNominal() + 6500);
            exampleTransaction.setTipeTransaksi((isDebit) ? TipeTransaksi.DEBIT : TipeTransaksi.KREDIT);
            isDebit = !isDebit;
            entityManager.persist(exampleTransaction);
        }

    }
}