package com.user_api.security.security.config;

import jakarta.security.auth.message.config.AuthConfig;
import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        // configurando o hash
    }

    @Bean
    public SecurityFilterChain securityFilterChain(@NonNull HttpSecurity http){
        http
                .csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/users").permitAll().anyRequest().authenticated()
                );
        return http.build();
        // cadastro sem autenticação
    }

}
