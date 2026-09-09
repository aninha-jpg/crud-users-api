package com.user_api.security.security;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Users {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String senha;

    public Users(UsersRequestDTO data){
        this.name = data.name();
        this.email = data.email();
        this.senha = data.senha();
    }
}
