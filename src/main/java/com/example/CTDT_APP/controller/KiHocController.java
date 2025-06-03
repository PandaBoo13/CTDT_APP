package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.request.KiHocCreationRequest;
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

}
