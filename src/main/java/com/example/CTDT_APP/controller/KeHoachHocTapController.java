package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.response.ApiResponse;
import com.example.CTDT_APP.service.KeHoachHocTapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/ke-hoach-hoc-tap")
public class KeHoachHocTapController {

    private final KeHoachHocTapService keHoachHocTapService;

    @GetMapping("/{maCTDT}")
    public ResponseEntity<ApiResponse> getAllKeHoachHocTap(@PathVariable String maCTDT) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("")
                .data(keHoachHocTapService.getAllKehoachHocTap(maCTDT))
                .build();
        return ResponseEntity.ok(response);
    }
}
