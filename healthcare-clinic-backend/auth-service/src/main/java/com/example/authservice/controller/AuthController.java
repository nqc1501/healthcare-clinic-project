package com.example.authservice.controller;

import com.example.authservice.payload.req.LoginRequest;
import com.example.authservice.service.CredentialService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    CredentialService sCredential;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(sCredential.register(request, response));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(sCredential.signIn(request, response));
    }

    @GetMapping("/check-login")
    public ResponseEntity<Boolean> checkLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.ok(false);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        return ResponseEntity.ok(sCredential.logout(response));
    }
}
