package com.user_api.security.security.services;

import com.user_api.security.security.entities.Users;
import com.user_api.security.security.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// classe que carega o usuario pro banco e fornece as autorizações pro spring security

@Service
public class UsersDetailsService implements UserDetailsService {

    private UsersRepository repository;

    @Autowired
    public UsersDetailsService(UsersRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = repository.findByEmail(email);
        if (users == null) {
            throw new UsernameNotFoundException("User Not Found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(
                users.getEmail(),
                users.getSenha(),
                users.getAuthorities()
        );
    }
}
