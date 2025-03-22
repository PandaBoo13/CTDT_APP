package com.example.CTDT_APP.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {
    private int code;
    private String message;
    private Object data;
}
