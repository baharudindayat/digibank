package com.digibank.restapi.repository;

import com.digibank.restapi.model.entity.Rekening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RekeningRepository extends JpaRepository<Rekening, Long> {

}
