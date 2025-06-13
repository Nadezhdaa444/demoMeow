package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "users_schema", name = "t_hobbies")
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "c_type")
    String type;

    @ManyToMany(mappedBy = "hobbies")
    Set<User> users;
}
