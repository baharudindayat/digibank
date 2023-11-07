package com.digibank.restapi.repository;

import com.digibank.restapi.model.entity.TypeRekening;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRekeningRepository extends JpaRepository<TypeRekening, Long> {
    @Override
    @NotNull
    List<TypeRekening> findAll();
}
