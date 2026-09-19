package com.user_api.security.security.entities;


import com.user_api.security.security.DTO.UsersRequestDTO;
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
    @Column(unique = true)
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private UsersProfile userProfile;

    public Users(UsersRequestDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.senha = data.senha();
    }

}
