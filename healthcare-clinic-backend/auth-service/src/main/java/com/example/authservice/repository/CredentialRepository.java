package com.example.authservice.repository;

import com.example.authservice.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Integer> {

    Optional<Credential> findByEmail(String email);
}
