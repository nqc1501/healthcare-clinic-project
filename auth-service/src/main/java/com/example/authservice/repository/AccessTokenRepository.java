package com.example.authservice.repository;

import com.example.authservice.model.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface AccessTokenRepository extends JpaRepository<AccessToken, Integer> {
}
