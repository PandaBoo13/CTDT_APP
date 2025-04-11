package com.example.CTDT_APP.controller;


import com.example.CTDT_APP.dto.response.ApiResponse;
import com.example.CTDT_APP.service.NhanVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/nhan-vien")
public class NhanVienController {
    private final NhanVienService nhanVienService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllNhanVien() {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Lấy danh sách nhân viên thành công")
                .data(nhanVienService.getAllNhanVien())
                .build();
        return ResponseEntity.ok(response);
    }
}
