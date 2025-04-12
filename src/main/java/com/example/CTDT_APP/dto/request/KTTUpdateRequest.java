package com.example.CTDT_APP.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class KTTUpdateRequest {
    @NotBlank(message = "Tên khối không được để trống")
    private String tenKhoi;
    private String moTa;
}
