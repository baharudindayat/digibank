package com.digibank.restapi.repository;

import com.digibank.restapi.model.Ktp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KtpRepository extends JpaRepository<Ktp, String> {
    Optional<Ktp> findByNik(String nik);
}