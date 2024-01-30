package com.example.authservice.services;

import com.example.authservice.dto.req.LoginRequest;
import com.example.authservice.dto.req.RegisterRequest;
import com.example.authservice.models.AccessToken;
import com.example.authservice.models.Credential;
import com.example.authservice.payload.AppResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CredentialService {

    AppResponse signIn(LoginRequest request);

    List<Credential> findAllAccounts();

    Credential findAccountById(Integer id);

    Credential findAccountByEmail(String email);

    Credential updateAccount(LoginRequest request);

    void deleteAccount(Integer id);

    AppResponse addDoctorAccount(RegisterRequest request);

    AppResponse addPatientAccount(RegisterRequest request);
}
