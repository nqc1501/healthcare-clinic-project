package com.example.authservice.controller;

import com.example.authservice.dto.req.LoginRequest;
import com.example.authservice.service.CredentialService;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

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

    @PostMapping("/check-login")
    public ResponseEntity<Boolean> checkLogin(HttpServletRequest request) {
        return ResponseEntity.ok(sCredential.checkLogin(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) throws ParseException, JOSEException {
        sCredential.logout(request, response);
        return ResponseEntity.ok("Đăng xuất thành công");
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(HttpServletRequest request, HttpServletResponse response) throws ParseException, JOSEException {
        return ResponseEntity.ok(sCredential.refresh(request, response));
    }
}
