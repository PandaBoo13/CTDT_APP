package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.request.NganhDaoTaoCreationRequest;
import com.example.CTDT_APP.dto.request.NganhDaoTaoUpdateRequest;
import com.example.CTDT_APP.dto.response.ApiResponse;
import com.example.CTDT_APP.service.NganhDaoTaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/nganh-dao-tao")
public class NganhDaoTaoController {

    private final NganhDaoTaoService nganhDaoTaoService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllNganhDaoTao() {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Lấy danh sách ngành đào tạo thành công")
                .data(nganhDaoTaoService.getAllNganhDaoTao())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createNganhDaoTao(@RequestBody NganhDaoTaoCreationRequest req) {
        ApiResponse response = ApiResponse.builder()
                .code(201)
                .message("Tạo ngành đào tạo thành công")
                .data(nganhDaoTaoService.createNganhDaoTao(req))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{maNganh}")
    public ResponseEntity<ApiResponse> updateNganhDaoTao(
            @PathVariable String maNganh, @RequestBody NganhDaoTaoUpdateRequest req
    ) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Cập nhật ngành đào tạo thành công")
                .data(nganhDaoTaoService.updateNganhDaoTao(maNganh, req))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{maNganh}")
    public ResponseEntity<ApiResponse> deleteNganhDaoTao(@PathVariable String maNganh) {
        nganhDaoTaoService.deleteNganhDaoTao(maNganh);
        ApiResponse response = ApiResponse.builder()
                .code(204)
                .message("Xóa ngành đào tạo thành công")
                .build();
        return ResponseEntity.ok(response);
    }
}
