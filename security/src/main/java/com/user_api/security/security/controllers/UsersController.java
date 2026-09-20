package com.user_api.security.security.controllers;

import com.user_api.security.security.entities.Users;
import com.user_api.security.security.repositories.UsersRepository;
import com.user_api.security.security.DTO.UsersRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.user_api.security.security.DTO.UsersResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UsersController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @CrossOrigin(origins ="*", allowedHeaders = "*")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void saveUsers(@RequestBody UsersRequestDTO data){
        String hashPassword = passwordEncoder.encode(data.senha());
        Users usersData = new Users(data);
        usersData.setSenha(hashPassword);
        repository.save(usersData);
    }
    //lista as informações do usuario
    @GetMapping("/me")
    public ResponseEntity<UsersResponseDTO> getMe(Authentication authentication) {
        String email = authentication.getName();

        Users user = repository.findByEmail(email);

        return ResponseEntity.ok(new UsersResponseDTO(user));
    }

}