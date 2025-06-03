package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.response.ApiResponse;
import com.example.CTDT_APP.service.NamDaoTaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/nam-dao-tao")
public class NamDaoTaoController {

    private final NamDaoTaoService namDaoTaoService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllNamDaoTao() {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Lấy danh sách năm đào tạo thành công")
                .data(namDaoTaoService.getAllNamDaoTao())
                .build();
        return ResponseEntity.ok(response);
    }
}
