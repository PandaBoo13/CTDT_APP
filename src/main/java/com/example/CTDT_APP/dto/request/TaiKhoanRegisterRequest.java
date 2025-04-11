package com.example.CTDT_APP.dto.request;

import com.example.CTDT_APP.constant.GioiTinh;
import com.example.CTDT_APP.constant.TrangThai;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class TaiKhoanRegisterRequest {
    private String tenDangNhap;
    private String matKhau;
    private String hoVaTen;
    private String email;
    private String soDienThoai;
    private GioiTinh gioiTinh;
    private LocalDate ngayThangNamSinh;
    private TrangThai trangThai;
}
