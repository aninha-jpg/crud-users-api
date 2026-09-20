package com.user_api.security.security.services;

import com.user_api.security.security.entities.Users;
import com.user_api.security.security.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UsersRepository repository;

    //verificando e criando toker jwt
    public String verify(Users users){
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(users.getEmail(), users.getSenha()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken((UserDetails) authentication.getPrincipal());
        }
        return "fail";
    }
}
