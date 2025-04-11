package com.example.CTDT_APP.dto.response;

import lombok.Data;

@Data
public class NhanVienResponse {
    private String maNhanVien;
    private String hoTen;
    private String email;
    private String soDienThoai;
    private String ngayThangNamSinh;
    private String gioiTinh;
    private String trangThai;
}
