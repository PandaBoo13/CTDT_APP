package com.example.CTDT_APP.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class KhoaUpdateRequest {
    @NotBlank(message = "Tên khoa không được để trống")
    private String tenKhoa;
}
