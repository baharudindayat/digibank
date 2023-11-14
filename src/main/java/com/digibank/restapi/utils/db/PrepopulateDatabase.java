package com.digibank.restapi.utils.db;

import com.digibank.restapi.model.entity.*;
import com.digibank.restapi.model.enums.AccountStatus;
import com.digibank.restapi.model.enums.JenisTransaksi;
import com.digibank.restapi.model.enums.TipeTransaksi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PrepopulateDatabase implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void run(String... args)  {
        //bank
        Bank bsi = new Bank();
        bsi.setNamaBank("Bank Syariah Indonesia");
        entityManager.persist(bsi);

        //user
        User userDevano = new User();
        userDevano.setEmail("devanozaidan@gmail.com");
        userDevano.setPassword("12345");
        userDevano.setStatusUser(AccountStatus.ACTIVE);
        userDevano.setMpin("898725");
        userDevano.setActive(true);
        userDevano.setCountBlockedMpin(0);
        entityManager.persist(userDevano);

        User userKepin = new User();
        userKepin.setEmail("kevin@gmail.com");
        userKepin.setPassword("12345");
        userKepin.setStatusUser(AccountStatus.ACTIVE);
        userKepin.setMpin("898725");
        userKepin.setActive(true);
        userKepin.setCountBlockedMpin(0);
        entityManager.persist(userKepin);


        //CIF
        CIF cifDevano = new CIF();
        cifDevano.setNik("1231231231312313");
        cifDevano.setNamaLengkap("Muhammad Devano Zaidan");
        cifDevano.setAlamat("example address");
        cifDevano.setPekerjaan("example job");
        cifDevano.setPenghasilan("2000000");
        cifDevano.setIdUser(userDevano);
        entityManager.persist(cifDevano);

        CIF cifKepin = new CIF();
        cifKepin.setNik("1231231231312315");
        cifKepin.setNamaLengkap("Kevin raihan saleh");
        cifKepin.setAlamat("example address");
        cifKepin.setPekerjaan("example job");
        cifKepin.setPenghasilan("2000000");
        cifKepin.setIdUser(userKepin);
        entityManager.persist(cifKepin);

        //tipe rekening
        TypeRekening tipeRekeningGold = new TypeRekening();
        tipeRekeningGold.setNamaTipe("GOLD");
        tipeRekeningGold.setLimitTransfer("200000000");
        entityManager.persist(tipeRekeningGold);

        //rekening
        Rekening devano = new Rekening();
        devano.setSaldo(3000000.0);
        devano.setTipeRekening(tipeRekeningGold);
        devano.setIdCif(cifDevano);
        entityManager.persist(devano);

        Rekening kepin = new Rekening();
        kepin.setSaldo(2000000.0);
        kepin.setTipeRekening(tipeRekeningGold);
        kepin.setIdCif(cifKepin);
        entityManager.persist(kepin);

        //transaksi
        boolean isDebit = true;
        for (int i = 0; i < 100; i++) {
            Transaksi exampleTransaction = new Transaksi();
            exampleTransaction.setNominal(230000000.0 + i);
            exampleTransaction.setRekeningAsal(devano);
            exampleTransaction.setRekeningTujuan(kepin);
            exampleTransaction.setCatatan("sedekah");
            exampleTransaction.setBank(bsi);
            exampleTransaction.setJenisTransaksi(JenisTransaksi.PINDAHBUKU);
            exampleTransaction.setTotalTransaksi(exampleTransaction.getNominal() + 6500);
            exampleTransaction.setTipeTransaksi((isDebit) ? TipeTransaksi.DEBIT : TipeTransaksi.KREDIT);
            isDebit = !isDebit;
            entityManager.persist(exampleTransaction);
        }

    }
}