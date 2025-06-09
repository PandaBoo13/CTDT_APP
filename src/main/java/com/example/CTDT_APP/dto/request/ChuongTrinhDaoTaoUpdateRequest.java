package com.example.CTDT_APP.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class ChuongTrinhDaoTaoUpdateRequest {
    @NotBlank(message = "Tên CTDT không được để trống")
    private String tenCTDT;

    private String moTa;

    @NotBlank(message = "Trạng thái không được để trống")
    private String trangThai;

    @NotBlank(message = "Mã bậc đào tạo không được để trống")
    private String maBac;

    @NotBlank(message = "Mã hình thức đào tạo không được để trống")
    private String maHe;

    @NotBlank(message = "Mã ngành đào tạo không được để trống")
    private String maNganh;

    private Set<Integer> namDaoTao;
}
