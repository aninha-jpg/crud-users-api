package com.user_api.security.security.controllers;

import com.user_api.security.security.DTO.LoginRequestDTO;
import com.user_api.security.security.entities.Users;
import com.user_api.security.security.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private UsersRepository repository;

    private final PasswordEncoder passwordEncoder;

    public AuthController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO data){
        Optional<Users> optionalUser = repository.findByEmail(data.email());
        if(optionalUser.isPresent()){
            Users user = optionalUser.get();
            boolean truePassword = passwordEncoder.matches(data.senha(), user.getSenha());
            if(truePassword){
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

