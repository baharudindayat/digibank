package com.digibank.restapi.service;

import com.digibank.restapi.dto.mpin.CreateMpinDto;
import com.digibank.restapi.exception.ResponseBadRequestException;
import com.digibank.restapi.exception.ResponseUnauthorizationException;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.repository.UserRepository;
import com.digibank.restapi.service.impl.CreateMpinServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CreateMpinServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateMpinServiceImpl createMpinService;

    @Test
    public void testCreateMpin() {

        int userId = 1;

        User user = User.builder()
                .idUser(1)
                .mpin("123456")
                .countBlockedMpin(0).build();
        CreateMpinDto createMpinDto = CreateMpinDto.builder()
                .mpin("123456")
                .build();

        when(userRepository.save(Mockito.any(User.class)))
                .thenReturn(user);

        when(userRepository.findById(Long.valueOf(userId)))
                .thenReturn(Optional.ofNullable(user));

        CreateMpinDto savedMpin = createMpinService.createMpin(Long.valueOf(userId), createMpinDto);

        assertNotNull(savedMpin);

    }

    @Test
    void testConfirmMpinWithCorrectMpin() {

        int userId = 1;

        CreateMpinDto createMpinDto = new CreateMpinDto("123456");
        User user = new User();
        user.setIdUser(userId);
        user.setMpin("123456");

        when(userRepository.findById(Long.valueOf(userId)))
                .thenReturn(Optional.of(user));

        CreateMpinDto result = createMpinService.confirmMpin(Long.valueOf(userId), createMpinDto);

        assertNotNull(result);
        assertEquals(user.getMpin(), result.getMpin());

    }

    @Test
    void testConfirmMpinWithIncorrectMpin() {

        int userId = 1;

        CreateMpinDto createMpinDto = new CreateMpinDto("123123");
        User user = new User();
        user.setIdUser(userId);
        user.setMpin("123456");

        when(userRepository.findById(Long.valueOf(userId)))
                .thenReturn(Optional.of(user));

        assertThrows(ResponseBadRequestException.class, () -> createMpinService.confirmMpin(Long.valueOf(userId), createMpinDto));

    }

    @Test
    void testConfirmMpinWithNonExistingUser() {

        int userId = 1;

        CreateMpinDto createMpinDto = new CreateMpinDto("123456");

        when(userRepository.findById(Long.valueOf(userId)))
                .thenReturn(Optional.empty());

        assertThrows(ResponseUnauthorizationException.class, () -> createMpinService.confirmMpin(Long.valueOf(userId), createMpinDto));

    }

}