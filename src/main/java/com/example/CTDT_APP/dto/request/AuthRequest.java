package com.example.CTDT_APP.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRequest {
    private String tenDangNhap;
    private String matKhau;
}
