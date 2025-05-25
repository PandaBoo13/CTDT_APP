package com.example.CTDT_APP.dto.response;

import com.example.CTDT_APP.constant.NgonNgu;
import com.example.CTDT_APP.constant.TrangThai;
import com.example.CTDT_APP.entity.KhoiKienThuc;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
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