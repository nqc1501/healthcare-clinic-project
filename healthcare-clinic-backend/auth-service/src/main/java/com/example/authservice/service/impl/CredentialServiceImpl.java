package com.example.authservice.service.impl;

import com.example.authservice.dto.req.LoginRequest;
import com.example.authservice.dto.req.RegisterRequest;
import com.example.authservice.dto.res.LoginResponse;
import com.example.authservice.enums.Role;
import com.example.authservice.model.AccessToken;
import com.example.authservice.model.Credential;
import com.example.authservice.model.InvalidatedToken;
import com.example.authservice.repository.AccessTokenRepository;
import com.example.authservice.repository.CredentialRepository;
import com.example.authservice.repository.InvalidatedTokenRepository;
import com.example.authservice.service.CredentialService;
import com.example.authservice.utils.JwtUtils;
import com.example.responsehandling.payload.response.AppResponse;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
@FieldDefaults(level= AccessLevel.PRIVATE)
public class CredentialServiceImpl implements CredentialService {

    @Value("${jwt.valid-duration}")
    private Long VALID_DURATION;

    @Value("${jwt.refreshable-duration}")
    private Long REFRESHABLE_DURATION;

    private final String SECRET_KEY = "eDr/NgGRj2RRo1xRw6+EYfGUqbG424KYCgZ0vPIt/qWkfAgy8sl9dysxOaDyBmho";

    @Autowired
    CredentialRepository rCredential;

    @Autowired
    AccessTokenRepository rToken;

    @Autowired
    InvalidatedTokenRepository rInvalidatedToken;

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

        Cookie cookie = new Cookie("JWT", token);
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

        Cookie cookie = new Cookie("JWT", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(12 * 60 * 60);
        response.addCookie(cookie);

        return new AppResponse(new LoginResponse(
                token,
                credential.getId(),
                credential.getRole()
        ));
    }

    @Override
    public boolean checkLogin(HttpServletRequest request) {
        String token = jwtUtils.getJwtFromCookies(request);
        boolean isValid = true;

        if (token != null) {
            try {
                verifyToken(token, false);
            } catch (RuntimeException | ParseException e) {
                isValid = false;
            }
        } else {
            isValid = false;
        }

        return isValid;
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
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ParseException {

        String token = jwtUtils.getJwtFromCookies(request);

        if (token != null) {
            log.info("Token: {}", token);
            Claims claims = verifyToken(token, false);

            String jti = claims.getId();
            Date expiryTime = claims.getExpiration();

            InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                    .id(jti)
                    .expiryTime(expiryTime)
                    .build();

            rInvalidatedToken.save(invalidatedToken);
        }

        Cookie cookie = new Cookie("JWT", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    @Override
    public AppResponse refresh(HttpServletRequest request, HttpServletResponse response) throws ParseException {

        String token = jwtUtils.getJwtFromCookies(request);

        log.info("Token in refresh function: {}", token);

        if (token != null) {
            Claims claims = verifyToken(token, true);

            String jti = claims.getId();
            Date expiryTime = claims.getExpiration();

            rInvalidatedToken.save(InvalidatedToken.builder()
                    .id(jti)
                    .expiryTime(expiryTime)
                    .build());

            String username = claims.getSubject();

            Credential user = rCredential.findByEmail(username).orElseThrow();

            String refreshToken = jwtUtils.generateToken(user);

            Cookie cookie = new Cookie("JWT", refreshToken);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(12 * 60 * 60);
            response.addCookie(cookie);

            return new AppResponse(refreshToken);
        }

        return new AppResponse("Token does not exist.");
    }

    private Claims verifyToken(String token, boolean isRefresh) throws ParseException {

        Claims claims = jwtUtils.extractAllClaims(token);

        Date expiryTime = (isRefresh)
                ? new Date(claims
                    .getIssuedAt()
                    .toInstant()
                    .plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS)
                    .toEpochMilli())
                : claims.getExpiration();

        boolean verified = jwtUtils.verifyToken(token);

        if (!(verified && expiryTime.after(new Date()))) throw new RuntimeException("Invalid token");

        if (rInvalidatedToken.findById(claims.getId()).isPresent())
            throw new RuntimeException("Account logged out.");

        return claims;
    }
}
