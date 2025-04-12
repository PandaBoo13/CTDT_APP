package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.request.TaiKhoanLoginRequest;
import com.example.CTDT_APP.dto.request.TaiKhoanRegisterRequest;
import com.example.CTDT_APP.dto.response.ApiResponse;
import com.example.CTDT_APP.service.TaiKhoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/tai-khoan")
@RequiredArgsConstructor
public class TaiKhoanController {
    private final TaiKhoanService taiKhoanService;

    @PostMapping("/dang-nhap")
    public ResponseEntity<ApiResponse> dangNhap(@RequestBody TaiKhoanLoginRequest request) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Đăng nhập thành công")
                .data(taiKhoanService.login(request))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/dang-ki")
    public ResponseEntity<ApiResponse> dangKi(@RequestBody TaiKhoanRegisterRequest request) {
        ApiResponse response = ApiResponse.builder()
                .code(201)
                .message("Tạo tài khoản thành công")
                .data(taiKhoanService.register(request))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/block/{maTaiKhoan}")
    public ResponseEntity<ApiResponse> blockTaiKhoan(@PathVariable String maTaiKhoan) {
        taiKhoanService.blockTaiKhoan(maTaiKhoan);
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Khóa tài khoản thành công")
                .build();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/active/{maTaiKhoan}")
    public ResponseEntity<ApiResponse> activeTaiKhoan(@PathVariable String maTaiKhoan) {
        taiKhoanService.activeTaiKhoan((maTaiKhoan));
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Kích hoạt tài khoản thành công")
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("validate-token")
    public ResponseEntity<ApiResponse> validateToken(@RequestBody String token) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Kiểm tra token thành công")
                .data(taiKhoanService.isValidToken(token))
                .build();
        return ResponseEntity.ok(response);
    }

}
