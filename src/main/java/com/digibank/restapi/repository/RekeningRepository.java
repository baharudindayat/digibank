package com.digibank.restapi.repository;

import com.digibank.restapi.model.entity.CIF;
import com.digibank.restapi.model.entity.Rekening;
import com.digibank.restapi.model.entity.dukcapil.Ktp;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RekeningRepository extends JpaRepository<Rekening, Long> {
    List<Rekening> findByidCif(CIF idCif);
    Optional<Rekening> findByNoRekening(long nik);

    @NotNull
    List<Rekening> findAll();
}
