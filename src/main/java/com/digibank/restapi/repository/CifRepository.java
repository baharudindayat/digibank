package com.digibank.restapi.repository;

import com.digibank.restapi.model.entity.CIF;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CifRepository extends JpaRepository<CIF, Long> {
}
