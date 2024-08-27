package com.example.authservice.service;

import com.example.authservice.model.Credential;
import com.example.authservice.payload.req.LoginRequest;
import com.example.authservice.payload.req.RegisterRequest;
import com.example.responsehandling.payload.response.AppResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface CredentialService {

    AppResponse register(LoginRequest request, HttpServletResponse response);

    AppResponse signIn(LoginRequest request, HttpServletResponse response);

    Credential getById(Integer id);

    AppResponse addPatientAccount(RegisterRequest request);

    AppResponse changePassword(Integer id, RegisterRequest request);

    AppResponse updateAccount(Credential credential);

    AppResponse logout(HttpServletResponse response);
}
