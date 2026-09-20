package com.user_api.security.security.repositories;


import com.user_api.security.security.DTO.UsersRequestDTO;
import com.user_api.security.security.entities.Users;
import com.user_api.security.security.entities.UsersProfile;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UsersRepositoryTest {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get user successfully from db")
    void findByEmailSuccess() {
        UsersRequestDTO data = new UsersRequestDTO("Ana", "ana@email.com", "2983334", UsersProfile.CLIENT);
        this.createUser(data);

        Users foundUser = this.usersRepository.findByEmail("ana@email.com");

        assertThat(foundUser).isNotNull();
    }

    @Test
    @DisplayName("Should not get user from db shen user not exist")
    void findByEmailNegative() {
        Users foundUser = this.usersRepository.findByEmail("ana@email.com");

        assertThat(foundUser).isNull();
    }

    private Users createUser(UsersRequestDTO users){
        Users newUser = new Users(users);
        this.entityManager.persist(newUser);
        return newUser;
    }

}