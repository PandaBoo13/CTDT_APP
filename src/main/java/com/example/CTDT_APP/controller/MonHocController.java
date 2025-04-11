package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.request.MonHocCreationRequest;
import com.example.CTDT_APP.dto.response.ApiResponse;
import com.example.CTDT_APP.service.MonHocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/mon-hoc")
public class MonHocController {
    private final MonHocService monHocService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllMonHoc() {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Lấy danh sách môn học thành công")
                .data(monHocService.getAllMonHoc())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createMonHoc(@RequestBody MonHocCreationRequest req) {
        ApiResponse response = ApiResponse.builder()
                .code(201)
                .message("Tạo môn học thành công")
                .data(monHocService.createMonHoc(req))
                .build();
        return ResponseEntity.ok(response);
    }
}
