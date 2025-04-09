package com.example.CTDT_APP.dto.request;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaiKhoanCreationRequest {
    private String tenDangNhap;
    private String matKhau;
}
