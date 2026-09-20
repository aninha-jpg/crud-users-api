package com.user_api.security.security.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.util.ReflectionTestUtils;


class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setup() {
        jwtService = new JwtService();

        ReflectionTestUtils.setField(
                jwtService,
                "jwtSecret",
                "teste-unitario-geracao-do-token--oiiiii!"
        );

        ReflectionTestUtils.setField(
                jwtService,
                "jwtExpirationMS",
                1800000
        );

        jwtService.init();
    }

    @Test
    @DisplayName("Should generate JWT successfully")
    void generateToken() {
        UserDetails userDetails = User
                .withUsername("ana@email.com")
                .password("123")
                .authorities("ROLE_CLIENT")
                .build();

        String token = jwtService.generateToken(userDetails);
        assertThat(token).isNotNull();
    }


    @Test
    @DisplayName("Should get user from token sucessfully")
    void getUserFromToken() {
        UserDetails userDetails = User
                .withUsername("ana@email.com")
                .password("123")
                .authorities("ROLE_CLIENT")
                .build();

        String token = jwtService.generateToken(userDetails);
        String username = jwtService.getUserFromToken(token);
        assertThat(username).isEqualTo("ana@email.com");
    }

    @Test
    @DisplayName("shoul validation token jwt successfully")
    void validateJwtToken() {
        UserDetails userDetails = User
                .withUsername("ana@email.com")
                .password("123")
                .authorities("ROLE_CLIENT")
                .build();

        String token = jwtService.generateToken(userDetails);
        boolean result = jwtService.validateJwtToken(token);
        assertThat(result).isTrue();
    }
}