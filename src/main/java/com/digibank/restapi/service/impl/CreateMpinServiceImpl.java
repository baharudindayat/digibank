package com.digibank.restapi.service.impl;


import com.digibank.restapi.dto.CreateMpinDto;
import com.digibank.restapi.mapper.CreateMpinMapper;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.repository.UserRepository;
import com.digibank.restapi.service.CreateMpinService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@AllArgsConstructor
@Slf4j
public class CreateMpinServiceImpl implements CreateMpinService {

    private UserRepository userRepository;

    @Override
    public CreateMpinDto createMpin(Long idUser, CreateMpinDto createMpinDto) {

        int idUserInt = idUser.intValue();
        User user = userRepository.findById(idUserInt)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not Found"));


//        createMpinDto.setMpin(createMpinDto.getMpin());
        user.setMpin(createMpinDto.getMpin());
//        User mpin = CreateMpinMapper.MAPPER.mapToCreateMpin(createMpinDto);
        User savedMpin = userRepository.save(user);

        return CreateMpinMapper.MAPPER.mapToCreateMpinDto(savedMpin);

    }
}
