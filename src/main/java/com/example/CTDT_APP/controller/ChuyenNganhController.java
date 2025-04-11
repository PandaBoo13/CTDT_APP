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
                .message("Lấy danh sách chuyên ngành thành công")
                .data(chuyenNganhService.getAllChuyenNganh())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createChuyenNganh(@RequestBody ChuyenNganhCreationRequest req) {
        ApiResponse response = ApiResponse.builder()
                .code(201)
                .message("Tạo chuyên ngành thành công")
                .data(chuyenNganhService.createChuyenNganh(req))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{maChuyenNganh}")
    public ResponseEntity<ApiResponse> updateChuyenNganh(@PathVariable String maChuyenNganh, @RequestBody ChuyenNganhUpdateRequest req) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Cập nhật chuyên ngành thành công")
                .data(chuyenNganhService.updateChuyenNganh(maChuyenNganh, req))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{maChuyenNganh}")
    public ResponseEntity<ApiResponse> deleteChuyenNganh(@PathVariable String maChuyenNganh) {
        chuyenNganhService.deleteChuyenNganh(maChuyenNganh);
        ApiResponse response = ApiResponse.builder()
                .code(204)
                .message("Xóa chuyên ngành thành công")
                .build();
        return ResponseEntity.ok(response);
    }
}
