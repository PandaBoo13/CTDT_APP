package com.example.CTDT_APP.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChuongTrinhDaoTaoCreationRequest {
    @NotBlank(message = "Mã CTDT không được để trống")
    private String maCTDT;

    @NotBlank(message = "Tên CTDT không được để trống")
    private String tenCTDT;

    private String moTa;

    @NotBlank(message = "Trạng thái không được để trống")
    private String trangThai;  // Ví dụ: "HOAT_DONG" hoặc "NGUNG_HOAT_DONG"

    @NotBlank(message = "Mã bậc đào tạo không được để trống")
    private String maBac;

    @NotBlank(message = "Mã hình thức đào tạo không được để trống")
    private String maHe;

    @NotBlank(message = "Mã ngành đào tạo không được để trống")
    private String maNganh;
}
