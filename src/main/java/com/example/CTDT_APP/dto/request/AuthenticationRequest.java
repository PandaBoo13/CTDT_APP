package com.example.CTDT_APP.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationRequest {
    String tenDangNhap;
    String matKhau;
}
