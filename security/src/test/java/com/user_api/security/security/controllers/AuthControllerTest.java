package com.user_api.security.security.controllers;

import com.user_api.security.security.DTO.UsersRequestDTO;
import com.user_api.security.security.entities.Users;
import com.user_api.security.security.entities.UsersProfile;
import com.user_api.security.security.repositories.UsersRepository;
import com.user_api.security.security.services.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DataJpaTest
@ActiveProfiles("test")
class AuthControllerTest {

    @Mock
    private UsersRepository repository;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;

    @Autowired
    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void login() {
        UsersRequestDTO data = new UsersRequestDTO("Ana", "ana@email.com", "2983334", UsersProfile.CLIENT);
        Authentication authentication = mock(Authentication.class);
        UserDetails usersDetails = mock(UserDetails.class);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(usersDetails);
        when(jwtService.generateToken(usersDetails)).thenReturn("Token-Falso");
        String result = authController.login(data);
        assertThat(result).isEqualTo("Token-Falso");
    }

    @Test
    @DisplayName("when the user already exists")
    void registerUserCaseOne() {
        UsersRequestDTO data = new UsersRequestDTO("Ana", "ana@email.com", "2983334", UsersProfile.CLIENT);
        when(repository.existsByEmail(data.email())).thenReturn(true);
        String result = authController.registerUser(data);
        assertThat(result).isEqualTo("Users already exists!");
        verify(repository, never()).save(any(Users.class));
    }

    @Test
    @DisplayName("when the user does not exist")
    void registerUserCaseTwo() {
        UsersRequestDTO data = new UsersRequestDTO("Ana", "ana@email.com", "2983334", UsersProfile.CLIENT);
        when(repository.existsByEmail(data.email())).thenReturn(false);
        when(passwordEncoder.encode(data.senha())).thenReturn("senha-criptografada");
        String result = authController.registerUser(data);
        assertThat(result).isEqualTo("User registered successfully!");
        verify(repository).save(any(Users.class));
    }

}