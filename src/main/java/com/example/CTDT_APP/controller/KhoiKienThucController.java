package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.request.KhoiKienThucCreationRequest;
import com.example.CTDT_APP.dto.response.ApiResponse;
import com.example.CTDT_APP.service.KhoiKienThucService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/khoi-kien-thuc")
public class KhoiKienThucController {
    private final KhoiKienThucService khoiKienThucService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllKhoiKienThuc() {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Lấy danh sách khối kiến thức thành công")
                .data(khoiKienThucService.getAllKhoiKienThuc())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createKhoiKienThuc(@RequestBody KhoiKienThucCreationRequest req) {
        ApiResponse response = ApiResponse.builder()
                .code(201)
                .message("Tạo khối kiến thức thành công")
                .data(khoiKienThucService.createKhoiKienThuc(req))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{maKhoi}")
    public ResponseEntity<ApiResponse> updateKhoiKienThuc(
            @PathVariable String maKhoi, @RequestBody KhoiKienThucCreationRequest req
    ) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Cập nhật khối kiến thức thành công")
                .data(khoiKienThucService.updateKhoiKienThuc(maKhoi, req))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{maKhoi}")
    public ResponseEntity<ApiResponse> deleteKhoiKienThuc(@PathVariable String maKhoi) {
        khoiKienThucService.deleteKhoiKienThuc(maKhoi);
        ApiResponse response = ApiResponse.builder()
                .code(204)
                .message("Xóa khối kiến thức thành công")
                .build();
        return ResponseEntity.ok(response);
    }
}
