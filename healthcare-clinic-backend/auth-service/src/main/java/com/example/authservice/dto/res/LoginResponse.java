package com.example.authservice.dto.res;

import com.example.authservice.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String token;
    private String type = "Bearer ";
    private Integer id;
    private Role role;

    public LoginResponse(String accessToken, Integer id, Role role) {
        this.token = accessToken;
        this.id = id;
        this.role = role;
    }
}
