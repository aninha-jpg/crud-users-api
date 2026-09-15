package com.user_api.security.security.repositories;

import com.user_api.security.security.entities.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String name);
    Users findByEmail(String email);
    boolean existsByUsername(String username);
}
