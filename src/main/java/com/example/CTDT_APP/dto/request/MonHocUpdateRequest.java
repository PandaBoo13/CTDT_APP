package com.example.CTDT_APP.dto.request;

import com.example.CTDT_APP.constant.NgonNgu;
import com.example.CTDT_APP.constant.TrangThai;
import lombok.Data;

@Data
public class MonHocUpdateRequest {
    private String maMon;
    private String tenMon;
    private int soTinChi;
    private int soTietLyThuyet;
    private int soTietBaiTap;
    private int soTietThucHanh;
    private int soTietTuHoc;
    private NgonNgu ngonNguGiangDay;
    private TrangThai trangThai;
    private String maKhoi;
}
