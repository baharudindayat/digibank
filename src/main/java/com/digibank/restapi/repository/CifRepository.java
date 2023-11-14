package com.digibank.restapi.repository;

import com.digibank.restapi.model.entity.CIF;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CifRepository extends JpaRepository<CIF, Long> {

    Optional<CIF> findByNik(String nik);

}
