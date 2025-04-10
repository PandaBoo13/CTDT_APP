package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.request.BacDaoTaoCreationRequest;
import com.example.CTDT_APP.dto.request.BacDaoTaoUpdateRequest;
import com.example.CTDT_APP.dto.response.ApiResponse;
import com.example.CTDT_APP.service.BacDaoTaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/bac-dao-tao")
public class BacDaoTaoController {
    private final BacDaoTaoService bacDaoTaoService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllBacDaoTao() {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Lay danh sach bac dao tao thanh cong")
                .data(bacDaoTaoService.getAllBacDaoTao())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createBacDaoTao(@RequestBody BacDaoTaoCreationRequest req) {
        ApiResponse response = ApiResponse.builder()
                .code(201)
                .message("Tao moi bac dao tao thanh cong")
                .data(bacDaoTaoService.createBacDaoTao(req))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{maBacDaoTao}")
    public ResponseEntity<ApiResponse> updateBacDaoTao(
            @PathVariable String maBacDaoTao,
            @RequestBody BacDaoTaoUpdateRequest req
    ) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Cap nhat bac dao tao thanh cong")
                .data(bacDaoTaoService.updateBacDaoTao(maBacDaoTao, req))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{maBacDaoTao}")
    public ResponseEntity<ApiResponse> deleteBacDaoTao(@PathVariable String maBacDaoTao) {
        bacDaoTaoService.deleteBacDaoTao(maBacDaoTao);
        ApiResponse response = ApiResponse.builder()
                .code(202)
                .message("Xoa bac dao tao thanh cong")
                .build();
        return ResponseEntity.ok(response);
    }
}
