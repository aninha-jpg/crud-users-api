package com.user_api.security.security.DTO;

import com.user_api.security.security.entities.Users;

public record UsersResponseDTO(Long id, String name, String email) {

    public UsersResponseDTO(Users users){
        this(users.getId(), users.getName(), users.getEmail());
    }

}
