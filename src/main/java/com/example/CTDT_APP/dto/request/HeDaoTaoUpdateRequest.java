package com.example.CTDT_APP.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HeDaoTaoUpdateRequest {
    @NotBlank(message = "Tên hệ đào tạo không được để trống")
    private String tenHe;
}
