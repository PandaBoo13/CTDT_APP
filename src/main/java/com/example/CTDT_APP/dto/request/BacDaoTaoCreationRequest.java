package com.example.CTDT_APP.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BacDaoTaoCreationRequest {
    @NotBlank(message = "Mã bậc đào tạo không được để trống")
    private String maBac;
    @NotBlank(message = "Tên bậc đào tạo không được để trống")
    private String tenCapBac;
    @NotBlank(message = "Thời gian đào tạo không được để trống")
    private Float thoiGianDaoTao;
}
