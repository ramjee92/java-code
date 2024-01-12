package com.ramjee.blogAppPractise.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",joinColumns = {@JoinColumn(name = "users_id",referencedColumnName = "id"),
    },inverseJoinColumns = {@JoinColumn(name = "roles_id",referencedColumnName = "id")})
    private Set<Role> roles=new HashSet<>();
}
