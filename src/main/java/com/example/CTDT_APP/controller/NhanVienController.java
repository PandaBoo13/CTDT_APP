package com.example.CTDT_APP.controller;


import com.example.CTDT_APP.dto.request.NhanVienUpdateRequest;
import com.example.CTDT_APP.dto.response.ApiResponse;
import com.example.CTDT_APP.service.NhanVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateNhanVien(@PathVariable String id, @RequestBody NhanVienUpdateRequest request) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Cập nhật nhân viên thành công")
                .data(nhanVienService.updateNhanVien(id, request))
                .build();
        return ResponseEntity.ok(response);
    }
}
