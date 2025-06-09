package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.request.KeHoachHocTapCreationRequest;
import com.example.CTDT_APP.dto.request.KeHoachHocTapUpdateRequest;
import com.example.CTDT_APP.dto.response.ApiResponse;
import com.example.CTDT_APP.service.KeHoachHocTapService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ke-hoach-hoc-tap")
@RequiredArgsConstructor
public class KeHoachHocTapController {

    private final KeHoachHocTapService keHoachHocTapService;

    @GetMapping("/{maCTDT}")
    public ResponseEntity<ApiResponse> getAllKeHoachHocTap(
            @PathVariable String maCTDT
    ) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Lấy danh sách kế hoạch học tập thành công")
                .data(keHoachHocTapService.getAllKeHoachHocTap(maCTDT))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{maCTDT}")
    public ResponseEntity<ApiResponse> createKeHoachHocTap(
            @PathVariable String maCTDT,
            @Valid @RequestBody KeHoachHocTapCreationRequest req
    ) {
        ApiResponse response = ApiResponse.builder()
                .code(201)
                .message("Tạo kế hoạch học tập thành công")
                .data(keHoachHocTapService.createKeHoachHocTap(maCTDT, req))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{maKHHT}")
    public ResponseEntity<ApiResponse> updateKeHoachHocTap(
            @PathVariable String maKHHT,
            @Valid @RequestBody KeHoachHocTapUpdateRequest req
    ) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Cập nhật kế hoạch học tập thành công")
                .data(keHoachHocTapService.updateKeHoachHocTap(maKHHT, req))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{maKHHT}")
    public ResponseEntity<ApiResponse> deleteKeHoachHocTap(
            @PathVariable String maKHHT
    ) {
        keHoachHocTapService.deleteKeHoachHocTap(maKHHT);
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Xoá kế hoạch học tập thành công")
                .build();
        return ResponseEntity.ok(response);
    }
}
