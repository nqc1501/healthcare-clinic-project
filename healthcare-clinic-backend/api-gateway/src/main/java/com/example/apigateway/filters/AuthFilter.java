package com.example.apigateway.filters;

import com.example.apigateway.config.RouteValidator;
import com.example.apigateway.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
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

                log.info("Validating...");

                HttpCookie cookie = exchange.getRequest().getCookies().getFirst("JWT");
                if (cookie == null) {
                    throw new RuntimeException("Invalid cookie");
                }

                String token = cookie.getValue();
                log.info(token);

                try {
                    if (jwtUtils.isExpired(token)) {
                        throw new RuntimeException("Token has expired");
                    }

                } catch (Exception e) {
                    throw new RuntimeException("Unauthorized access to application");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }


}
