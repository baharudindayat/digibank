package com.digibank.restapi.repository;

import com.digibank.restapi.model.entity.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransferRepository extends JpaRepository<Transaksi, Long> {
    Optional<Transaksi> findAccountsourceAndDestinationForTransfer(String accountsource, String accountdestination);
}
