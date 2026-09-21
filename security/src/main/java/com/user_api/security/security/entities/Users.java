package com.user_api.security.security.entities;


import com.user_api.security.security.DTO.UsersRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;

@Table(name = "users")
@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Users {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "Role")
    private Set<UsersProfile> userProfile = new HashSet<>();


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userProfile.stream().map(r -> new SimpleGrantedAuthority(r.name()))
                .toList();
    }

    public Users(){

    }
    public Users(UsersRequestDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.senha = data.senha();
    }

    public Users(UsersRequestDTO data, UsersProfile profile) {
        this.name = data.name();
        this.email = data.email();
        this.senha = data.senha();
        this.userProfile.add(profile);
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

}
