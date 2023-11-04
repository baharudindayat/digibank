package com.digibank.restapi.repository;

import com.digibank.restapi.model.entity.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaksi, Long> {
    @Query("SELECT t FROM Transaksi t WHERE (t.jenisTransaksi = 'DEBIT' AND :isDebit = true) OR (t.jenisTransaksi = 'KREDIT' AND :isKredit = true) " +
            "AND t.waktuTransaksi >= :tanggalMulai AND t.waktuTransaksi <= :tanggalAkhir")
    List<Transaksi> findFilteredTransactions(boolean isDebit, boolean isKredit, LocalDate tanggalMulai, LocalDate tanggalAkhir);
}
