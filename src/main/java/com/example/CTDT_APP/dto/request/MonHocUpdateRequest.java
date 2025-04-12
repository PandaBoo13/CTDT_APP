package com.example.CTDT_APP.dto.request;

import com.example.CTDT_APP.constant.NgonNgu;
import com.example.CTDT_APP.constant.TrangThai;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MonHocUpdateRequest {
    @NotBlank(message = "Tên môn học không được để trống")
    private String tenMon;
    private int soTinChi;
    private int soTietLyThuyet;
    private int soTietBaiTap;
    private int soTietThucHanh;
    private int soTietTuHoc;
    @NotBlank(message = "Ngôn ngữ giảng dạy không được để trống")
    private NgonNgu ngonNguGiangDay;
    private TrangThai trangThai;
    private String maKhoi;
}
