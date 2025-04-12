package com.example.CTDT_APP.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaiKhoanLoginRequest {
    @NotBlank(message = "Tên đăng nhập không được để trống")
    private String tenDangNhap;
    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    private String matKhau;
}
