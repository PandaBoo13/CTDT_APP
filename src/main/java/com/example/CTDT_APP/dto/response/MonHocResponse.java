package com.example.CTDT_APP.dto.response;

import com.example.CTDT_APP.constant.NgonNgu;
import com.example.CTDT_APP.constant.TrangThai;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class MonHocResponse {
    private String maMon;
    private String tenMon;
    private int soTinChi;
    private int soTietLyThuyet;
    private int soTietBaiTap;
    private int soTietThucHanh;
    private int soTietTuHoc;
    private NgonNgu ngonNguGiangDay;
    private String maKhoi;
    private TrangThai trangThai;
}
