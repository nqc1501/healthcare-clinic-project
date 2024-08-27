package com.example.apigateway.config;

import org.springframework.http.HttpMethod;

public class Endpoint {

    private String path;
    private HttpMethod method;

    public Endpoint(String path, HttpMethod method) {
        this.path = path;
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public HttpMethod getMethod() {
        return method;
    }
}
