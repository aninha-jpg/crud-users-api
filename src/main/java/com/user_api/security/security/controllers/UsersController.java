package com.user_api.security.security.controllers;

import com.user_api.security.security.config.SecurityConfig;
import com.user_api.security.security.entities.Users;
import com.user_api.security.security.repositories.UsersRepository;
import com.user_api.security.security.DTO.UsersRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @GetMapping
    public List<UsersResponseDTO> getAll() {
        List<UsersResponseDTO> usersList = repository.findAll()
                .stream()
                .map(UsersResponseDTO::new)
                .toList();
        return usersList;
    }

    @CrossOrigin(origins ="*", allowedHeaders = "*")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public void saveUsers(@RequestBody UsersRequestDTO data){
        String hashPassword = passwordEncoder.encode(data.senha());
        Users usersData = new Users(data);
        usersData.setSenha(hashPassword);
        repository.save(usersData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> attUser(@PathVariable Long id, @RequestBody UsersRequestDTO data){
        Optional<Users> optionalUser = repository.findById(id);
        if(optionalUser.isPresent()){
            Users user = optionalUser.get();
            user.setName(data.name());
            user.setEmail(data.email());
            repository.save(user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
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