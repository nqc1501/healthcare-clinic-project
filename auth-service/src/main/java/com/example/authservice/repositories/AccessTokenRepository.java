package com.example.authservice.repositories;

import com.example.authservice.models.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface AccessTokenRepository extends JpaRepository<AccessToken, Integer> {
}
