package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.request.KhoaCreationRequest;
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
                .message("Lay danh sach mon hoc thanh cong")
                .data(monHocService.getAllMonHoc())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createMonHoc(@RequestBody MonHocCreationRequest req) {
        ApiResponse response = ApiResponse.builder()
                .code(201)
                .message("Tao mon hoc thanh cong")
                .data(monHocService.createMonHoc(req))
                .build();
        return ResponseEntity.ok(response);
    }

//    @PutMapping
//    public ResponseEntity<ApiResponse> updateKhoa(@RequestBody MonHocUpdateRequest req) {
//        ApiResponse response = ApiResponse.builder()
//                .code(200)
//                .message("Cap nhat mon hoc thanh cong")
//                .data(monHocService.updateMonHoc(req.g)
//                .build();
//        return ResponseEntity.ok(response);
//    }
//
//    @DeleteMapping("/{maMon}")
//    public ResponseEntity<ApiResponse> deleteKhoa(@PathVariable String maMon) {
//        monHocService.deleteMonHoc(maMon);
//        ApiResponse response = ApiResponse.builder()
//                .code(204)
//                .message("Xoa mon hoc thanh cong")
//                .build();
//        return ResponseEntity.ok(response);
//    }
}
