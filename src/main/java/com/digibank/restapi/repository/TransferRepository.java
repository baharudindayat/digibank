package com.digibank.restapi.repository;

import com.digibank.restapi.model.entity.Rekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransferRepository extends JpaRepository<Rekening, Long> {
    Optional<Rekening> findByRekening(long rekening);
}
