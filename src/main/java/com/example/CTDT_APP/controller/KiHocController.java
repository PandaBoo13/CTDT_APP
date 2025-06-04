package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.request.KiHocCreationRequest;
import com.example.CTDT_APP.dto.request.KiHocMonHocCreationRequest;
import com.example.CTDT_APP.dto.request.KiHocUpdateRequest;
import com.example.CTDT_APP.dto.response.ApiResponse;
import com.example.CTDT_APP.service.KiHocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/ki-hoc")
public class KiHocController {
    private final KiHocService kiHocService;

    @GetMapping("/{maKHHT}")
    public ResponseEntity<ApiResponse> getAllKhoiKienThuc(
            @PathVariable String maKHHT
    ) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Lấy danh sách kì học thành công")
                .data(kiHocService.getAllKiHoc(maKHHT))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{maKHHT}")
    public ResponseEntity<ApiResponse> createKiHoc(
            @PathVariable String maKHHT,
            @RequestBody KiHocCreationRequest req
    ) {
        ApiResponse response = ApiResponse.builder()
                .code(201)
                .message("Tạo kì học thành công")
                .data(kiHocService.createKiHoc(maKHHT, req))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{maKi}")
    public ResponseEntity<ApiResponse> updateKiHoc(
            @PathVariable String maKi,
            @RequestBody KiHocUpdateRequest req
    ) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Cập nhật kì học thành công")
                .data(kiHocService.updateKiHoc(maKi, req))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{maKi}/mon-hoc")
    public ResponseEntity<ApiResponse> createMonHocToKiHoc(
            @PathVariable String maKi,
            @RequestBody KiHocMonHocCreationRequest req
    ) {
        ApiResponse response = ApiResponse.builder()
                .code(201)
                .message("Thêm môn học vào kì học thành công")
                .data(kiHocService.createMonHocToKiHoc(maKi, req))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{maKi}/mon-hoc")
    public ResponseEntity<ApiResponse> getMonHocByKiHoc(
            @PathVariable String maKi
    ) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Lấy danh sách môn học trong kì học thành công")
                .data(kiHocService.getMonHocByKiHoc(maKi))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{maKi}")
    public ResponseEntity<ApiResponse> deleteKiHoc(
            @PathVariable String maKi
    ) {
        kiHocService.deleteKiHoc(maKi);
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Xóa kì học thành công")
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{maKi}/mon-hoc/{maMon}")
    public ResponseEntity<ApiResponse> deleteMonHocFromKiHoc(
            @PathVariable String maKi,
            @PathVariable String maMon
    ) {
        kiHocService.deleteMonHocFromKiHoc(maKi, maMon);
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Xóa môn học khỏi kì học thành công")
                .build();
        return ResponseEntity.ok(response);
    }

}
