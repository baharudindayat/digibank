package com.digibank.restapi.digibank.repository;

import com.digibank.restapi.digibank.entity.tipe_rekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface tipe_rekeningRepository extends JpaRepository<tipe_rekening, Integer> {
}
