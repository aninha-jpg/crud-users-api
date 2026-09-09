package com.user_api.security.security.controllers;

import com.user_api.security.security.Users;
import com.user_api.security.security.UsersRepository;
import com.user_api.security.security.UsersRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.user_api.security.security.UsersResponseDTO;

import java.util.List;

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
    @PostMapping
    public void saveUsers(@RequestBody UsersRequestDTO data){
        Users usersData = new Users(data);
        repository.save(usersData);
        return;
    }


    @PutMapping
    public String teste3(){
        return "teste put";
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}