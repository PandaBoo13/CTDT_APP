package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.request.ChuongTrinhDaoTaoCreationRequest;
import com.example.CTDT_APP.dto.request.ChuongTrinhDaoTaoUpdateRequest;
import com.example.CTDT_APP.dto.response.ApiResponse;
import com.example.CTDT_APP.dto.response.ChuongTrinhDaoTaoResponse;
import com.example.CTDT_APP.service.ChuongTrinhDaoTaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ctdt")
@RequiredArgsConstructor
public class ChuongTrinhDaoTaoController {

    private final ChuongTrinhDaoTaoService ctdtService;

    // Read: Lấy danh sách CTDT (bao gồm list KeHoachHocTap)
    @GetMapping
    public ResponseEntity<ApiResponse> getAllCTDT() {
        List<ChuongTrinhDaoTaoResponse> list = ctdtService.getAllCTDT();
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Lấy danh sách CTDT thành công")
                .data(list)
                .build();
        return ResponseEntity.ok(response);
    }

    // Find: Tìm CTDT theo MaCTDT
    @GetMapping("/{maCTDT}")
    public ResponseEntity<ApiResponse> findCTDT(@PathVariable String maCTDT) {
        ChuongTrinhDaoTaoResponse data = ctdtService.findCTDT(maCTDT);
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Tìm thấy CTDT thành công")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    // Create: Tạo mới CTDT
    @PostMapping
    public ResponseEntity<ApiResponse> createCTDT(@Valid @RequestBody ChuongTrinhDaoTaoCreationRequest req) {
        ChuongTrinhDaoTaoResponse data = ctdtService.createCTDT(req);
        ApiResponse response = ApiResponse.builder()
                .code(201)
                .message("Tạo CTDT thành công")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    // Update: Cập nhật CTDT (ngoại trừ MaCTDT)
    @PutMapping("/{maCTDT}")
    public ResponseEntity<ApiResponse> updateCTDT(@PathVariable String maCTDT,
                                                  @Valid @RequestBody ChuongTrinhDaoTaoUpdateRequest req) {
        ChuongTrinhDaoTaoResponse data = ctdtService.updateCTDT(maCTDT, req);
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Cập nhật CTDT thành công")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    // Delete: Xóa CTDT theo MaCTDT
    @DeleteMapping("/{maCTDT}")
    public ResponseEntity<ApiResponse> deleteCTDT(@PathVariable String maCTDT) {
        ctdtService.deleteCTDT(maCTDT);
        ApiResponse response = ApiResponse.builder()
                .code(204)
                .message("Xóa CTDT thành công")
                .build();
        return ResponseEntity.ok(response);
    }
}
