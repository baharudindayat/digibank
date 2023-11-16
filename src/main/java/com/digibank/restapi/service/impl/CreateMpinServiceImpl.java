package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.CreateMpinDto;
import com.digibank.restapi.exception.ResponseBadRequestException;
import com.digibank.restapi.exception.ResponseUnauthorizationException;
import com.digibank.restapi.mapper.CreateMpinMapper;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.repository.UserRepository;
import com.digibank.restapi.service.CreateMpinService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CreateMpinServiceImpl implements CreateMpinService {

    private UserRepository userRepository;

    @Override
    public CreateMpinDto createMpin(Long idUser, CreateMpinDto createMpinDto) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new ResponseUnauthorizationException( "User tidak ditemukan"));

        user.setMpin(createMpinDto.getMpin());
        user.setCountBlockedMpin(0);
        User savedMpin = userRepository.save(user);

        return CreateMpinMapper.MAPPER.mapToCreateMpinDto(savedMpin);

    }

    @Override
    public CreateMpinDto confirmMpin(Long idUser, CreateMpinDto createMpinDto) {

        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new ResponseUnauthorizationException( "User tidak ditemukan"));
        if (Objects.equals(createMpinDto.getMpin(), user.getMpin())) {
            return CreateMpinMapper.MAPPER.mapToCreateMpinDto(user);
        } else  {
            throw new ResponseBadRequestException("MPIN tidak sama");
        }

    }

}
