package com.example.CTDT_APP.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChuyenNganhCreationRequest {
    @NotBlank(message = "Mã chuyên ngành không được để trống")
    private String maChuyenNganh;
    @NotBlank(message = "Tên chuyên ngành không được để trống")
    private String tenChuyenNganh;
    @NotBlank(message = "Mã ngành đào tạo không được để trống")
    private String maNganh;
}
