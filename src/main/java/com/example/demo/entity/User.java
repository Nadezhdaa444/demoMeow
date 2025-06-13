package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "users_schema", name = "t_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "c_name")
    String name;

    @Column(name = "c_age")
    int age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "c_passport_id")
    Passport passport;

    @ManyToMany
    @JoinTable(
            schema = "users_schema",  // Add schema
            name = "t_users_hobbies",  // Match actual table name
            joinColumns = @JoinColumn(name = "c_user_id"),
            inverseJoinColumns = @JoinColumn(name = "c_hobby_id")
    )
    Set<Hobby> hobbies;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Account> accounts;
}
