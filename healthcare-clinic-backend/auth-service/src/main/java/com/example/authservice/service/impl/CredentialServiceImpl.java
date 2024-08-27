package com.example.authservice.service.impl;

import com.example.authservice.enums.Role;
import com.example.authservice.model.AccessToken;
import com.example.authservice.model.Credential;
import com.example.authservice.payload.req.LoginRequest;
import com.example.authservice.payload.req.RegisterRequest;
import com.example.authservice.payload.res.LoginResponse;
import com.example.authservice.repository.AccessTokenRepository;
import com.example.authservice.repository.CredentialRepository;
import com.example.authservice.service.CredentialService;
import com.example.authservice.utils.CookieUtils;
import com.example.authservice.utils.JwtUtils;
import com.example.responsehandling.payload.response.AppResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CredentialServiceImpl implements CredentialService {

    @Autowired
    CredentialRepository rCredential;

    @Autowired
    AccessTokenRepository rToken;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public AppResponse register(LoginRequest request, HttpServletResponse response) {

        if (rCredential.findByEmail(request.getEmail()).isPresent()) {
            return new AppResponse("Tài khoản đã tồn tại", false);
        }

        var account = Credential.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        Credential credential = rCredential.save(account);

        String token = jwtUtils.generateToken(credential);

        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(12 * 60 * 60);
        response.addCookie(cookie);

        AccessToken accessToken = AccessToken.builder()
                .email(credential.getEmail())
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
    public AppResponse signIn(LoginRequest request, HttpServletResponse response) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Credential credential = rCredential.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtUtils.generateToken(credential);

        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(12 * 60 * 60);
        response.addCookie(cookie);

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
    public Credential getById(Integer id) {
        return rCredential.findById(id).orElse(null);
    }

    @Override
    public AppResponse addPatientAccount(RegisterRequest request) {

        try {
            if (rCredential.findByEmail(request.getEmail()).isPresent()) {
                return new AppResponse("Tài khoản đã tồn tại", false);
            }

            var account = Credential.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.PATIENT)
                    .build();

            Credential credential = rCredential.save(account);

            String token = jwtUtils.generateToken(credential);
            AccessToken accessToken = AccessToken.builder()
                    .email(credential.getEmail())
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

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    @Override
    public AppResponse changePassword(Integer id, RegisterRequest request) {

        try {

            Credential existingCredential = rCredential.findById(id).orElse(null);
            if (existingCredential == null) {
                return new AppResponse("Tài khoản không tồn tại", false);
            }

            if (request.getPassword().equals(existingCredential.getPassword())) {
                return new AppResponse("Mật khẩu không thay đổi", false);
            }

            existingCredential.setPassword(passwordEncoder.encode(request.getPassword()));

            return new AppResponse(rCredential.save(existingCredential));

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    @Override
    public AppResponse updateAccount(Credential credential) {
        try {

            Credential existingCredential = rCredential.findByEmail(credential.getEmail()).orElse(null);
            if (existingCredential == null) {
                return new AppResponse("Tài khoản không tồn tại", false);
            }

            existingCredential.setUserId(credential.getUserId());

            return new AppResponse(rCredential.save(existingCredential));

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    @Override
    public AppResponse logout(HttpServletResponse response) {
        CookieUtils.clear(response, "JWT");
        return new AppResponse("Đăng xuất thành công", true);
    }
}
