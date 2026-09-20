package com.user_api.security.security.repositories;

import com.user_api.security.security.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    boolean existsByEmail(String email);
}
