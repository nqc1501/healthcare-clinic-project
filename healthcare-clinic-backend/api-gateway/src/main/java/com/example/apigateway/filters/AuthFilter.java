package com.example.apigateway.filters;

import com.example.apigateway.config.RouteValidator;
import com.example.apigateway.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    @Autowired
    RouteValidator validator;

    @Autowired
    private JwtUtils jwtUtils;

    public AuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {

                HttpCookie cookie = exchange.getRequest().getCookies().getFirst("jwt");
                if (cookie == null) {
                    throw new RuntimeException("Cookie invalid");
                }

                String token = cookie.getValue();

                try {
                    if (jwtUtils.isExpired(token)) {
                        throw new RuntimeException("Token has expired");
                    }

                } catch (Exception e) {
                    throw new RuntimeException("Unauthorized access to application");
                }

//                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//                    throw new RuntimeException("Missing authorization header");
//                }
//
//                String authHeader = exchange.getRequest().getHeaders().getOrEmpty("Authorization").get(0);
//                String token = authHeader;
//
//                if (authHeader != null && authHeader.startsWith("Bearer ")) {
//                    authHeader = authHeader.substring(7);
//                }
//
//                try {
//                    if (jwtUtils.isExpired(authHeader)) {
//                        throw new RuntimeException("Token has expired");
//                    }
//
//                } catch (Exception e) {
//                    throw new RuntimeException("Unauthorized access to application");
//                }

                // Them jwt vao headers cua response
                return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                    ResponseCookie responseCookie = ResponseCookie.from("jwt", cookie.getValue())
                            .path("/")
                            .httpOnly(true)
                            .build();

                    exchange.getResponse().addCookie(responseCookie);
                }));
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }


}
