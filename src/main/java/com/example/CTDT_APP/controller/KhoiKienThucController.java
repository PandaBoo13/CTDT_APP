package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.request.KTTCreationRequest;
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
                .message("Lay danh sach khoi kien thuc thanh cong")
                .data(khoiKienThucService.getAllKhoiKienThuc())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createKhoiKienThuc(@RequestBody KTTCreationRequest req) {
        ApiResponse response = ApiResponse.builder()
                .code(201)
                .message("Tao khoi kien thuc thanh cong")
                .data(khoiKienThucService.createKhoiKienThuc(req))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{maKhoi}")
    public ResponseEntity<ApiResponse> updateKhoiKienThuc(
            @PathVariable String maKhoi, @RequestBody KTTCreationRequest req
    ) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Cap nhat khoi kien thuc thanh cong")
                .data(khoiKienThucService.updateKhoiKienThuc(maKhoi, req))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{maKhoi}")
    public ResponseEntity<ApiResponse> deleteKhoiKienThuc(@PathVariable String maKhoi) {
        khoiKienThucService.deleteKhoiKienThuc(maKhoi);
        ApiResponse response = ApiResponse.builder()
                .code(204)
                .message("Xoa khoi kien thuc thanh cong")
                .build();
        return ResponseEntity.ok(response);
    }
}
