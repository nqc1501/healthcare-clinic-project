package com.example.roomservice.payload;

import com.example.roomservice.constant.AppError;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class AppResponse {

    private int code = AppError.SUCCESS;
    private String message = "Thành công";
    private Object data;

    public AppResponse(Object data) {
        this.data = data;
    }

    public AppResponse(String error) {
        code = 1;
        message = error;
    }

    public AppResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public AppResponse(String message, boolean success) {
        this.message = message;
    }

    @JsonIgnore
    public void generateErrRes(HttpServletResponse response) throws IOException {
        final Map<String, Object> body = new HashMap<>();
        body.put("code", code);
        body.put("message", message);
        body.put("data", null);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(code);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}
