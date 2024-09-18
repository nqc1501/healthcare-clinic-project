package com.example.authservice.service;

import com.example.authservice.model.Credential;
import com.example.authservice.dto.req.LoginRequest;
import com.example.authservice.dto.req.RegisterRequest;
import com.example.responsehandling.payload.response.AppResponse;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.text.ParseException;

public interface CredentialService {

    AppResponse register(LoginRequest request, HttpServletResponse response);

    AppResponse signIn(LoginRequest request, HttpServletResponse response);

    boolean checkLogin(HttpServletRequest request);

    Credential getById(Integer id);

    AppResponse addPatientAccount(RegisterRequest request);

    AppResponse changePassword(Integer id, RegisterRequest request);

    AppResponse updateAccount(Credential credential);

    void logout(HttpServletRequest request, HttpServletResponse response) throws ParseException, JOSEException;

    AppResponse refresh(HttpServletRequest request, HttpServletResponse response) throws ParseException, JOSEException;
}
