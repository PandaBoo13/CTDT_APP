package com.example.CTDT_APP.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NganhDaoTaoCreationRequest {
    @NotBlank(message = "Mã ngành đào tạo không được để trống")
    private String maNganh;
    @NotBlank(message = "Tên ngành đào tạo (Tiếng Việt) không được để trống")
    private String tenNganhTV;
    @NotBlank(message = "Tên ngành đào tạo (Tiếng Anh) không được để trống")
    private String tenNganhTA;
    @NotBlank(message = "Mã khoa không được để trống")
    private String maKhoa;
}
