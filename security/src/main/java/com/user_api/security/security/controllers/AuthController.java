package com.user_api.security.security.controllers;

import com.user_api.security.security.DTO.UsersRequestDTO;
import com.user_api.security.security.entities.Users;
import com.user_api.security.security.entities.UsersProfile;
import com.user_api.security.security.repositories.UsersRepository;
import com.user_api.security.security.services.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private UsersRepository repository;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, UsersRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    @PostMapping("/login")
    public String login(@RequestBody UsersRequestDTO data){
        Authentication authentication = authenticationManager.authenticate(
            new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                    data.email(),
                    data.senha()
            )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtService.generateToken(userDetails.getUsername());
    }

    @PostMapping("/signup")
    public String registerUser(@RequestBody UsersRequestDTO data){
        if(repository.existsByEmail(data.email())){
            return "Users already exists!";
        } else {
            final Users newUsers = new Users(null, data.name(), data.email(), passwordEncoder.encode(data.senha()), UsersProfile.CLIENT
            );
            repository.save(newUsers);
            return "User registered successfully!";
        }
    }
}

