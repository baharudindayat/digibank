package com.digibank.restapi.repository;

import com.digibank.restapi.model.entity.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transaksi, Long> {

}
