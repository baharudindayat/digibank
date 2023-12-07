package com.digibank.restapi.repository;

import com.digibank.restapi.model.entity.Rekening;
import com.digibank.restapi.model.entity.Transaksi;
import com.digibank.restapi.model.enums.TipeTransaksi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaksi, Long>, JpaSpecificationExecutor<Transaksi> {

    Page<Transaksi> findAllByRekeningAsalIn(List<Rekening> rekeningAsal, Pageable pageable);

    @Query("SELECT t FROM Transaksi t WHERE (t.tipeTransaksi = :tipeTransaksi) AND (t.rekeningAsal IN :rekeningAsal)")
    Page<Transaksi> findByTipeTransaksiAndRekeningAsalInOrRekeningTujuanIn(
            @Param("tipeTransaksi") TipeTransaksi tipeTransaksi,
            @Param("rekeningAsal") List<Rekening> rekeningAsal,
            Pageable pageable);

    @Query("SELECT t FROM Transaksi t WHERE " +
            "(t.tipeTransaksi = :tipeTransaksi) AND " +
            "(t.waktuTransaksi BETWEEN :startDate AND :endDate) AND " +
            "(t.rekeningAsal IN :rekeningAsal)")
    Page<Transaksi> findByTipeTransaksiAndWaktuTransaksiBetweenAndRekeningAsalInOrRekeningTujuanIn(
            @Param("tipeTransaksi") TipeTransaksi tipeTransaksi,
            @Param("startDate") Timestamp startDate,
            @Param("endDate") Timestamp endDate,
            @Param("rekeningAsal") List<Rekening> rekeningAsal,
            Pageable pageable);

    @Query("SELECT t FROM Transaksi t WHERE " +
            "(t.waktuTransaksi BETWEEN :startDate AND :endDate) AND " +
            "(t.rekeningAsal IN :rekeningAsal)")
    Page<Transaksi> findByWaktuTransaksiBetweenAndRekeningAsalInOrRekeningTujuanIn(
            @Param("startDate") Timestamp startDate,
            @Param("endDate") Timestamp endDate,
            @Param("rekeningAsal") List<Rekening> rekeningAsal,
            Pageable pageable);
}
