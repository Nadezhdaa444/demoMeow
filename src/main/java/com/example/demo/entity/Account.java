package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "users_schema", name = "t_accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "c_title")
    String title;

    @ManyToOne
    @JoinColumn(name = "c_user_id")
    User user;
}
