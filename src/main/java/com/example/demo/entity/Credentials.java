
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(schema = "user_schema", name = "t_credentials")
public class Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "c_username", unique = true)
    private String username;

    @Column(nullable = false, name = "c_password")
    private String password;

   @ManyToOne
    @JoinColumn(name = "role_id")
    Role role ;
   @OneToOne(cascade = CascadeType.ALL)
           @JoinColumn(name = "c_user_id" , referencedColumnName = "id")

    User user;
}