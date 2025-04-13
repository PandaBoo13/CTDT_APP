package com.example.CTDT_APP.dto.request;

import com.example.CTDT_APP.constant.NgonNgu;
import com.example.CTDT_APP.constant.TrangThai;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class MonHocCreationRequest {
    @NotBlank(message = "Mã môn học không được để trống")
    private String maMon;
    @NotBlank(message = "Tên môn học không được để trống")
    private String tenMon;
    @NotBlank(message = "Số tín chỉ không được để trống")
    private int soTinChi;
    private int soTietLyThuyet;
    private int soTietBaiTap;
    private int soTietThucHanh;
    private int soTietTuHoc;
    private TrangThai trangThai;
    @NotBlank(message = "Ngôn ngữ giảng dạy không được để trống")
    private NgonNgu ngonNguGiangDay;
    @NotBlank(message = "Mã khối không được để trống")
    private String maKhoi;
    private List<QuanHeMonHocRequest> quanHeMonHoc;
}
