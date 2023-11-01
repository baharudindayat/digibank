package com.digibank.restapi.digibank.repository;

import com.digibank.restapi.digibank.entity.TipeRekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipeRekeningRepository extends JpaRepository<TipeRekening, Integer> {
}
