package com.example.CTDT_APP.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenValidationRequest {
    @NotBlank(message = "Token không được để trống")
    private String token;
}
