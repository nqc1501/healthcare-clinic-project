package com.example.userservice.constant;

import lombok.Data;

@Data
public class AppError {

    public static final int SUCCESS = 0;
    public static final int NO_DATA_FOUND = 404;
    public static final int INVALID_DATA = 1;
    public static final int INTERNAL_ERROR = 500;

    public static final String FORBIDDEN = "Không có quyền truy cập";
}
