package com.digibank.restapi.repository;

import com.digibank.restapi.model.entity.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, Long> {

}
