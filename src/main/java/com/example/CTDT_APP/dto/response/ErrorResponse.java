package com.example.CTDT_APP.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {
    @JsonProperty("error_code")
    private int code;
    @JsonProperty("error_message")
    private String message;
}
