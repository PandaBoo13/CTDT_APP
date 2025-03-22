package com.example.CTDT_APP.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class KhoaCreationRequest {
    @NotEmpty(message = "Ma khoa khong duoc de trong")
    private String maKhoa;
    private String tenKhoa;
}
