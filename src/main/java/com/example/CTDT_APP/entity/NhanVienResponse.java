package com.example.CTDT_APP.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class NhanVienResponse {
    private String maNV;
    private String maTaiKhoan;
    private String hoTen;
    private String email;
    private String soDienThoai;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate ngayThangNamSinh;

    private String gioiTinh;
    private String trangThai;
}