package com.example.CTDT_APP.dto.request;

import com.example.CTDT_APP.constant.GioiTinh;
import com.example.CTDT_APP.constant.TrangThai;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class TaiKhoanRegisterRequest {
    @NotBlank(message = "Tên đăng nhập không được để trống")
    private String tenDangNhap;

    @NotBlank(message = "Mật khẩu không được để trống")
    private String matKhau;

    @NotBlank(message = "Họ và tên không được để trống")
    private String hoVaTen;

    @Email(message = "Email không hợp lệ")
    private String email;

    @Pattern(
            regexp = "^[0-9]{10,15}$",
            message = "Số điện thoại không hợp lệ"
    )
    private String soDienThoai;

    private GioiTinh gioiTinh;

    private LocalDate ngayThangNamSinh;

    private TrangThai trangThai;
}
