package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.request.TaiKhoanLoginRequest;
import com.example.CTDT_APP.dto.request.TaiKhoanRegisterRequest;
import com.example.CTDT_APP.dto.response.ApiResponse;
import com.example.CTDT_APP.service.TaiKhoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/api/v1/tai-khoan")
@RequiredArgsConstructor
public class TaiKhoanController {
    private final TaiKhoanService taiKhoanService;

    @PostMapping("/dang-ki")
    public ResponseEntity<ApiResponse> dangKi(@RequestBody TaiKhoanRegisterRequest request) {
        ApiResponse response = ApiResponse.builder()
                .code(201)
                .message("Tạo tài khoản thành công")
                .data(taiKhoanService.register(request))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/dang-nhap")
    public ResponseEntity<ApiResponse> dangNhap(@RequestBody TaiKhoanLoginRequest request) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Đăng nhập thành công")
                .data(taiKhoanService.login(request))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/tam-ngung/{maTaiKhoan}")
    public ResponseEntity<ApiResponse> blockTaiKhoan(@RequestParam String maTaiKhoan) {
        taiKhoanService.blockTaiKhoan(maTaiKhoan);
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Khóa tài khoản thành công")
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("validate-token")
    public ResponseEntity<ApiResponse> validateToken(@RequestParam String token) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Kiểm tra token thành công")
                .data(taiKhoanService.isValidToken(token))
                .build();
        return ResponseEntity.ok(response);
    }

}
