package com.example.apigateway.config;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    public static final List<Endpoint> openApiEndpoints = List.of(
            new Endpoint("/api/v1/auth/**", null),
            new Endpoint("/eureka/**", null),
            new Endpoint("/api/v1/account", HttpMethod.POST),
            new Endpoint("/api/v1/specialty", HttpMethod.GET),
            new Endpoint("/api/v1/doctor/**", HttpMethod.GET),
            new Endpoint("/api/v1/doctor/specialty", HttpMethod.GET),
            new Endpoint("/api/v1/patient", HttpMethod.POST),
            new Endpoint("/api/v1/appointment/by-patient", HttpMethod.POST),
            new Endpoint("/api/v1/patient/by-health-code/**", HttpMethod.GET),
            new Endpoint("/api/v1/appointment/**/diagnosis", HttpMethod.GET),
            new Endpoint("/api/v1/prescription/find-by-diagnosis/**", HttpMethod.GET)
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(endpoint -> {
                        String path = request.getURI().getPath();
                        HttpMethod method = request.getMethod();

                        return pathMatches(endpoint.getPath(), path) &&
                                (endpoint.getMethod() == null || endpoint.getMethod().equals(method));
                    });

    private boolean pathMatches(String pattern, String path) {
        return pathMatcher.match(pattern, path);
    }
}
