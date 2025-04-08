package com.example.CTDT_APP.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenValidationRequest {
    private String token;
}
