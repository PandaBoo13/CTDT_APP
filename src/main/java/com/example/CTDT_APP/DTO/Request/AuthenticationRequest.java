package com.example.CTDT_APP.DTO.Request;

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
