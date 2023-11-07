package com.digibank.restapi.repository;

import com.digibank.restapi.model.entity.TypeRekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRekeningRepository extends JpaRepository<TypeRekening, Long> {

}
