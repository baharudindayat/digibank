package com.digibank.restapi.service;

import com.digibank.restapi.dto.changePassword.ChangePasswordDto;
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

    @Mock
    private JwtService jwtService;

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

        when(userRepository.findById((long) userId))
                .thenReturn(Optional.of(user));

        when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.<User>getArgument(0));

        CreatePasswordDto savedPasswordDto = passwordService.createPassword((long) userId, createPasswordDto);

        assertTrue(BCrypt.checkpw(newPassword, user.getPassword()));
        assertEquals(createPasswordDto, savedPasswordDto);
        Mockito.verify(userRepository, Mockito.times(1)).findById((long) userId);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);

    }

    @Test
    void testChangePasswordWithValidation() {

        String token = "your_sample_token";
        ChangePasswordDto changePasswordDto = new ChangePasswordDto();
        changePasswordDto.setOldPassword("old_password");
        changePasswordDto.setNewPassword("new_password");
        changePasswordDto.setConfirmPassword("new_password");

        User mockedUser = new User();
        mockedUser.setEmail("user@example.com");
        mockedUser.setPassword(BCrypt.hashpw("old_password", BCrypt.gensalt()));

        Mockito.when(jwtService.extractUserName(token)).thenReturn("user@example.com");
        Mockito.when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(mockedUser));

        CreatePasswordServiceImpl passwordService = new CreatePasswordServiceImpl(userRepository, jwtService);

        passwordService.changePasswordWithValidation(token, changePasswordDto);

        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

}
