package com.example.CTDT_APP.dto.request;

import com.example.CTDT_APP.constant.TrangThai;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class ChuongTrinhDaoTaoCreationRequest {
    @NotBlank(message = "Mã CTDT không được để trống")
    private String maCTDT;

    @NotBlank(message = "Tên CTDT không được để trống")
    private String tenCTDT;

    private String moTa;

    private TrangThai trangThai;

    @NotBlank(message = "Mã bậc đào tạo không được để trống")
    private String maBac;

    @NotBlank(message = "Mã hình thức đào tạo không được để trống")
    private String maHe;

    @NotBlank(message = "Mã ngành đào tạo không được để trống")
    private String maNganh;

    @NotNull(message = "Danh sách năm đào tạo không được để trống")
    private Set<Integer> namDaoTao;
}
