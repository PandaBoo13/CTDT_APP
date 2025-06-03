package com.example.CTDT_APP.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class KeHoachHocTapUpdateRequest {
    @NotBlank(message = "Mã chuyên ngành không được để trống")
    private String maChuyenNganh;
    private String moTa;
}
