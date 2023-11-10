package com.digibank.restapi.repository;

import com.digibank.restapi.model.entity.Transaksi;
import com.digibank.restapi.model.enums.TipeTransaksi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaksi, Long>, JpaSpecificationExecutor<Transaksi> {
    Page<Transaksi> findAllByTipeTransaksi(TipeTransaksi tipeTransaksi, Pageable pageable);

    Page<Transaksi> findAllByTipeTransaksiAndWaktuTransaksiBetween(TipeTransaksi tipeTransaksi,Timestamp startDate,Timestamp endDate,Pageable pageable);

    Page<Transaksi> findAllByWaktuTransaksiBetween(Timestamp startDate, Timestamp endDate, Pageable pageable);
}
