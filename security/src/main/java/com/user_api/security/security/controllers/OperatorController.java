package com.user_api.security.security.controllers;


import com.user_api.security.security.DTO.UsersRequestDTO;
import com.user_api.security.security.DTO.UsersResponseDTO;
import com.user_api.security.security.entities.Users;
import com.user_api.security.security.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/operator")
public class OperatorController {

    @Autowired
    private UsersRepository repository;
    //retorna o user p atualizar
    @CrossOrigin(origins ="*", allowedHeaders = "*")
    @GetMapping("/email/{email}")
    public ResponseEntity<UsersResponseDTO> getUserByEmail(@PathVariable String email) {

        Users user = repository.findByEmail(email);

        if (user != null) {
            return ResponseEntity.ok(new UsersResponseDTO(user));
        }

        return ResponseEntity.notFound().build();
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
}
