package com.user_api.security.security;

public record UsersResponseDTO(Long id, String name, String email) {

    public UsersResponseDTO(Users users){
        this(users.getId(), users.getName(), users.getEmail());
    }

}
