package com.example.CTDT_APP.dto.request;

import com.example.CTDT_APP.constant.NgonNgu;
import com.example.CTDT_APP.constant.TrangThai;
import com.example.CTDT_APP.dto.response.QuanHeMonHocResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MonHocUpdateRequest {
    @NotBlank(message = "Tên môn học không được để trống")
    private String tenMon;
    @NotBlank(message = "Số tín chỉ không được để trống")
    private int soTinChi;
    @NotBlank(message = "Số tiết lý thuyết không được để trống")
    private int soTietLyThuyet;
    @NotBlank(message = "Số tiết bài tập không được để trống")
    private int soTietBaiTap;
    @NotBlank(message = "Số tiết thực hành không được để trống")
    private int soTietThucHanh;
    @NotBlank(message = "Số tiết tự học không được để trống")
    private int soTietTuHoc;
    @NotBlank(message = "Ngôn ngữ giảng dạy không được để trống")
    private NgonNgu ngonNguGiangDay;
    private TrangThai trangThai;
    @NotBlank(message = "Khối kiến thức không được để trống")
    private String maKhoi;
    private List<QuanHeMonHocRequest> quanHeMonHoc;
}
