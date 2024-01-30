package com.example.authservice.services.impl;

import com.example.authservice.dto.req.LoginRequest;
import com.example.authservice.dto.req.RegisterRequest;
import com.example.authservice.dto.res.LoginResponse;
import com.example.authservice.enums.Role;
import com.example.authservice.models.AccessToken;
import com.example.authservice.models.Credential;
import com.example.authservice.payload.AppResponse;
import com.example.authservice.repositories.AccessTokenRepository;
import com.example.authservice.repositories.CredentialRepository;
import com.example.authservice.services.CredentialService;
import com.example.authservice.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CredentialServiceImpl implements CredentialService {

    private static final Logger logger = LoggerFactory.getLogger(CredentialServiceImpl.class);

    @Autowired
    private CredentialRepository rCredential;

    @Autowired
    private AccessTokenRepository rToken;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AppResponse signIn(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Credential credential = rCredential.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtUtils.generateToken(credential);

        AccessToken accessToken = AccessToken.builder()
                .email(request.getEmail())
                .accessToken(token)
                .issuedAt(jwtUtils.getIssuedAt(token))
                .expiredAt(jwtUtils.getExpiration(token))
                .build();
        rToken.save(accessToken);

        return new AppResponse(new LoginResponse(
                token,
                credential.getId(),
                credential.getRole()
        ));
    }

    @Override
    public List<Credential> findAllAccounts() {
        return rCredential.findAll();
    }

    @Override
    public Credential findAccountById(Integer id) {
        return rCredential.findById(id).orElse(null);
    }

    @Override
    public Credential findAccountByEmail(String email) {
        return rCredential.findByEmail(email).orElse(null);
    }

    @Override
    public Credential updateAccount(LoginRequest request) {
        return null;
    }

    @Override
    public void deleteAccount(Integer id) {
    }

    @Override
    public AppResponse addDoctorAccount(RegisterRequest request) {

        Optional<Credential> dbCre = rCredential.findByEmail(request.getEmail());
        if (dbCre.isPresent()) {
            return new AppResponse("Tài khoản đã tồn tại", false);
        }

        Credential credential = Credential.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.DOCTOR)
                .build();

        rCredential.save(credential);

        return new AppResponse("Tài khoản đã lưu thành công", true);
    }

    @Override
    public AppResponse addPatientAccount(RegisterRequest request) {
        Optional<Credential> dbCre = rCredential.findByEmail(request.getEmail());
        if (dbCre.isPresent()) {
            return new AppResponse("Tài khoản đã tồn tại", false);
        }

        Credential credential = Credential.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.PATIENT)
                .build();

        rCredential.save(credential);

        return new AppResponse("Tài khoản đã lưu thành công", true);
    }
}
