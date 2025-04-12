package com.example.CTDT_APP.dto.request;

import com.example.CTDT_APP.constant.DieuKienMonHoc;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QuanHeMonHocRequest {
    @NotBlank(message = "Mã môn học liên quan không được để trống")
    private String maMon;
    @NotBlank(message = "Điều kiện môn học không được để trống")
    private DieuKienMonHoc dieuKien;
}
