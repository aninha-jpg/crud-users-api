package com.user_api.security.security.controllers;

import com.user_api.security.security.DTO.UsersRequestDTO;
import com.user_api.security.security.DTO.UsersResponseDTO;
import com.user_api.security.security.entities.Users;
import com.user_api.security.security.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @CrossOrigin(origins ="*", allowedHeaders = "*")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void saveUsers(@RequestBody UsersRequestDTO data){
        String hashPassword = passwordEncoder.encode(data.senha());

        Users usersData = new Users(data, data.usersProfile());

        usersData.setSenha(hashPassword);
        repository.save(usersData);
    }


    @CrossOrigin(origins ="*", allowedHeaders = "*")
    @GetMapping
    public List<UsersResponseDTO> getAll() {
        List<UsersResponseDTO> usersList = repository.findAll()
                .stream()
                .map(UsersResponseDTO::new)
                .toList();
        return usersList;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
