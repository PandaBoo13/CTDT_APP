package com.example.CTDT_APP.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class KhoiKienThucCreationRequest {
    @NotBlank(message = "Mã khối không được để trống")
    private String maKhoi;
    @NotBlank(message = "Tên khối không được để trống")
    private String tenKhoi;
    private String moTa;
    private String parent;
}
