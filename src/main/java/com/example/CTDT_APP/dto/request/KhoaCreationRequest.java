package com.example.CTDT_APP.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class KhoaCreationRequest {
    @NotBlank(message = "Mã khoa không được để trống")
    private String maKhoa;
    @NotBlank(message = "Tên khoa không được để trống")
    private String tenKhoa;
}
