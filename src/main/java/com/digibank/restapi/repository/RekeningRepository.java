package com.digibank.restapi.repository;

import com.digibank.restapi.model.entity.Rekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Repository
public interface RekeningRepository extends JpaRepository<Rekening, Long> {

}
