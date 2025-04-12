package com.example.CTDT_APP.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChuyenNganhUpdateRequest {
    @NotBlank(message = "Tên chuyên ngành không được để trống")
    private String tenChuyenNganh;
    @NotBlank(message = "Mã chuyên ngành không được để trống")
    private String maNganh;
}
