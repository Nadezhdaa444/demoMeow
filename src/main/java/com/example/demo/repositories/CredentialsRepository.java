package com.example.demo.repositories;

import com.example.demo.entity.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialsRepository extends JpaRepository<Credentials,Long> {
    Optional<Credentials> findByUserName(String name);
}
