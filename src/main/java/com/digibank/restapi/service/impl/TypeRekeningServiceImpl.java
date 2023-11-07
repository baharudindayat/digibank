package com.digibank.restapi.service.impl;

import com.digibank.restapi.model.entity.TypeRekening;
import com.digibank.restapi.repository.TypeRekeningRepository;
import com.digibank.restapi.service.TypeRekeningService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.PrimitiveIterator;

@Service
@AllArgsConstructor
public class TypeRekeningServiceImpl implements TypeRekeningService {

    private TypeRekeningRepository typeRekeningRepository;

    @Override
    public List<TypeRekening> getAllTypeRekening() {
        @NotNull List<TypeRekening> optionalTypeRekening = typeRekeningRepository.findAll();
        return optionalTypeRekening;
    }
}
