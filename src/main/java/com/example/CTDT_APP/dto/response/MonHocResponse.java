package com.example.CTDT_APP.dto.response;

import com.example.CTDT_APP.entity.KhoiKienThuc;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class MonHocResponse {
    private String maMon;
    private String tenMon;
    private int soTinChi;
    private int soTietLyThuyet;
    private int soTietBaiTap;
    private int soTietThucHanh;
    private int soTietTuHoc;
    private String ngonNguGiangDay;
    private String trangThai;
    private KhoiKienThuc khoiKienThuc;
    private List<QuanHeMonHocResponse> quanHeMonHoc;
}