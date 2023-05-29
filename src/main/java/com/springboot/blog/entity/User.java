package com.springboot.blog.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    // FetchType.EAGER means whenever we load User entity, it will load Role as well
    // CascadeType: whenever we perform any operation on parent, it will be applicable to its child as well
    // eg. whenever you save user(parent), it will save its role(child) as well
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
                joinColumns = @JoinColumn(name = "user_id",  referencedColumnName = "id"), // id is a primary key in users table and user_id is a foreign key in users_roles table
                inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") // id is primary key in roles table and role_id is foreign key in users_roles table
    )
    private Set<Role> roles;


}
