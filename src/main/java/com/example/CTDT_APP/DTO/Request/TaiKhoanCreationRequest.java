package com.example.CTDT_APP.DTO.Request;

import jakarta.persistence.Id;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaiKhoanCreationRequest {
    private String maTaiKhoan;
    private String tenDangNhap;
    private String matKhau;
    private String maVaiTro;
}
