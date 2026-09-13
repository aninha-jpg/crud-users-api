package com.user_api.security.security.services;

import com.user_api.security.security.entities.Users;
import com.user_api.security.security.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UsersDetailsService implements UsersDetailsService {

    private UsersRepository repository;

    @Autowired
    public UsersDetailsService(UsersRepository repository){
        return null;
    }

    @Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        Users users = repository.findByEmail(email);
        if(users == null){
            throw new UsernameNotFoundException("User Not Found with email: " + email);

        }
    }

}
