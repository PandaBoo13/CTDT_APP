package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.request.MonHocCreationRequest;
import com.example.CTDT_APP.dto.request.MonHocUpdateRequest;
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
        System.out.print("Creating MonHoc: " + req);
        ApiResponse response = ApiResponse.builder()
                .code(201)
                .message("Tạo môn học thành công")
                .data(monHocService.createMonHoc(req))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateMonHoc(@PathVariable String id ,@RequestBody MonHocUpdateRequest req) {
        ApiResponse response = ApiResponse.builder()
                .message("Update môn học thành công")
                .data(monHocService.updateMonHoc(id, req))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMonHoc(@PathVariable String id) {
        monHocService.deleteMonHoc(id);
        ApiResponse response = ApiResponse.builder()
                .code(204)
                .message("Xoá môn học thành công")
                .build();
        return ResponseEntity.ok(response);
    }
}
