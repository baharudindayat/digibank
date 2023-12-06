package com.digibank.restapi.service;

import com.digibank.restapi.dto.createPassword.CreatePasswordDto;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.repository.UserRepository;
import com.digibank.restapi.service.impl.CreatePasswordServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PasswordServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreatePasswordServiceImpl passwordService;

    @Test
    public void CreatePassworTest() {

        int userId = 1;
        String newPassword = "alamsyah";

        User user = User.builder()
                .idUser(1)
                .email("alamsyah@gmail.com")
                .password("alamsyah").build();
        CreatePasswordDto createPasswordDto = CreatePasswordDto.builder()
                .password(newPassword).build();

        when(userRepository.findById(Long.valueOf(userId)))
                .thenReturn(Optional.of(user));

        when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(invocationOnMock -> {
                   User savedPassword = invocationOnMock.getArgument(0);
                   return savedPassword;
                });

        CreatePasswordDto savedPasswordDto = passwordService.createPassword(Long.valueOf(userId), createPasswordDto);

        assertTrue(BCrypt.checkpw(newPassword, user.getPassword()));
        assertEquals(createPasswordDto, savedPasswordDto);
        Mockito.verify(userRepository, Mockito.times(1)).findById(Long.valueOf(userId));
        Mockito.verify(userRepository, Mockito.times(1)).save(user);

    }

}
