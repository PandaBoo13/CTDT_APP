package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.request.ChuyenNganhCreationRequest;
import com.example.CTDT_APP.dto.request.ChuyenNganhUpdateRequest;
import com.example.CTDT_APP.dto.response.ApiResponse;
import com.example.CTDT_APP.service.ChuyenNganhService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/chuyen-nganh")
public class ChuyenNganhController {
    private final ChuyenNganhService chuyenNganhService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllChuyenNganh() {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Lay danh sach chuyen nganh thanh cong")
                .data(chuyenNganhService.getAllChuyenNganh())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createChuyenNganh(@RequestBody ChuyenNganhCreationRequest req) {
        ApiResponse response = ApiResponse.builder()
                .code(201)
                .message("Tao chuyen nganh thanh cong")
                .data(chuyenNganhService.createChuyenNganh(req))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{maChuyenNganh}")
    public ResponseEntity<ApiResponse> updateChuyenNganh(@PathVariable String maChuyenNganh, @RequestBody ChuyenNganhUpdateRequest req) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Cap nhat chuyen nganh thanh cong")
                .data(chuyenNganhService.updateChuyenNganh(maChuyenNganh, req))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{maChuyenNganh}")
    public ResponseEntity<ApiResponse> deleteChuyenNganh(@PathVariable String maChuyenNganh) {
        chuyenNganhService.deleteChuyenNganh(maChuyenNganh);
        ApiResponse response = ApiResponse.builder()
                .code(204)
                .message("Xoa chuyen nganh thanh cong")
                .build();
        return ResponseEntity.ok(response);
    }
}
