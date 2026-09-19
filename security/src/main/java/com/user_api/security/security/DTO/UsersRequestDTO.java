package com.user_api.security.security.DTO;

import com.user_api.security.security.entities.UsersProfile;

public record UsersRequestDTO(String name, String email, String senha, UsersProfile usersProfile) {

}
