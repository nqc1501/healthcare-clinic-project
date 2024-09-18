package com.example.apigateway.repository;

import com.example.apigateway.dto.res.CheckLoginResponse;
import org.springframework.http.MediaType;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface AuthClient {

    @PostExchange(url = "/api/v1/auth/check-login", contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<CheckLoginResponse> checkLogin();
}
