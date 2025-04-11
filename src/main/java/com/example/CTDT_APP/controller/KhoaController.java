package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.request.KhoaCreationRequest;
import com.example.CTDT_APP.dto.request.KhoaUpdateRequest;
import com.example.CTDT_APP.dto.response.ApiResponse;
import com.example.CTDT_APP.service.KhoaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/khoa")
public class KhoaController {
    private final KhoaService khoaService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllKhoa() {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Lấy danh sách khoa thành công")
                .data(khoaService.getAllKhoa())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createKhoa(@RequestBody @Valid KhoaCreationRequest req) {
        ApiResponse response = ApiResponse.builder()
                .code(201)
                .message("Tạo khoa thành công")
                .data(khoaService.createKhoa(req))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{maKhoa}")
    public ResponseEntity<ApiResponse> updateKhoa(
            @PathVariable String maKhoa, @RequestBody KhoaUpdateRequest req
    ) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Cập nhật khoa thành công")
                .data(khoaService.updateKhoa(maKhoa, req))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{maKhoa}")
    public ResponseEntity<ApiResponse> deleteKhoa(@PathVariable String maKhoa) {
        khoaService.deleteKhoa(maKhoa);
        ApiResponse response = ApiResponse.builder()
                .code(204)
                .message("Xóa khoa thành công")
                .build();
        return ResponseEntity.ok(response);
    }
}
