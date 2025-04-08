package com.example.CTDT_APP.dto.request;

import com.example.CTDT_APP.constant.NgonNgu;
import com.example.CTDT_APP.constant.TrangThai;
import lombok.Data;

import java.util.List;

@Data
public class MonHocCreationRequest {
    private String maMon;
    private String tenMon;
    private int soTinChi;
    private int soTietLyThuyet;
    private int soTietBaiTap;
    private int soTietThucHanh;
    private int soTietTuHoc;
    private TrangThai trangThai;
    private NgonNgu ngonNguGiangDay;
    private String maKhoi;
    private List<QuanHeMonHocRequest> quanHeMonHocs;
}
