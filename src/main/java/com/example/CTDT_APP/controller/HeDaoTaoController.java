package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.request.HeDaoTaoCreationRequest;
import com.example.CTDT_APP.dto.request.HeDaoTaoUpdateRequest;
import com.example.CTDT_APP.dto.response.ApiResponse;
import com.example.CTDT_APP.service.HeDaoTaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/he-dao-tao")
public class HeDaoTaoController {
    private final HeDaoTaoService heDaoTaoService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllHeDaoTao() {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Lay danh sach he dao tao thanh cong")
                .data(heDaoTaoService.getAllHeDaoTao())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createHeDaoTao(@RequestBody HeDaoTaoCreationRequest req) {
        ApiResponse response = ApiResponse.builder()
                .code(201)
                .message("Tao moi he dao tao thanh cong")
                .data(heDaoTaoService.createHeDaoTao(req))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{maHeDaoTao}")
    public ResponseEntity<ApiResponse> updateHeDaoTao(
            @PathVariable String maHeDaoTao,
            @RequestBody HeDaoTaoUpdateRequest req
    ) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Cap nhat he dao tao thanh cong")
                .data(heDaoTaoService.updateHeDaoTao(maHeDaoTao, req))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{maHeDaoTao}")
    public ResponseEntity<ApiResponse> deleteHeDaoTao(@PathVariable String maHeDaoTao) {
        heDaoTaoService.deleteHeDaoTao(maHeDaoTao);
        ApiResponse response = ApiResponse.builder()
                .code(202)
                .message("Xoa he dao tao thanh cong")
                .build();
        return ResponseEntity.ok(response);
    }
}
