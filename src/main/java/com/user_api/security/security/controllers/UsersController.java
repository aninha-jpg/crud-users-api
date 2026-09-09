package com.user_api.security.security.controllers;

import com.user_api.security.security.Users;
import com.user_api.security.security.UsersRepository;
import com.user_api.security.security.UsersRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.user_api.security.security.UsersResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository repository;

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
    @PostMapping
    public void saveUsers(@RequestBody UsersRequestDTO data){
        Users usersData = new Users(data);
        repository.save(usersData);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> attUser(@PathVariable Long id, @RequestBody UsersRequestDTO data){
        Optional<Users> optionalUser = repository.findById(id);
        if(optionalUser.isPresent()){
            Users user = optionalUser.get();
            user.setName(data.name());
            user.setEmail(data.email());
            user.setSenha(data.senha());
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