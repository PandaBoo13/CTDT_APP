package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.request.TaiKhoanCreationRequest;
import com.example.CTDT_APP.dto.request.TaiKhoanUpdateRequest;
import com.example.CTDT_APP.dto.response.ApiResponse;
import com.example.CTDT_APP.service.TaiKhoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("taikhoans")
@RequiredArgsConstructor
public class TaiKhoanController {
    private final TaiKhoanService taiKhoanService;

    @PostMapping
    public ResponseEntity<ApiResponse> createTaiKhoan(@RequestBody TaiKhoanCreationRequest request) {
        ApiResponse response = ApiResponse.builder()
                .code(201)
                .message("Create TaiKhoan successful")
                .data(taiKhoanService.createTaikhoan(request))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getTaikhoan() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Get TaiKhoan successful")
                .data(taiKhoanService.getTaiKhoan())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{mataikhoan}")
    ResponseEntity<ApiResponse> getTaiKhoanById(@PathVariable("mataikhoan") String mataikhoan) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Get TaiKhoan successful")
                .data(taiKhoanService.getTaiKhoanById(mataikhoan))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{maTaiKhoan}")
    ResponseEntity<ApiResponse> updateTaiKhoanById(
            @PathVariable("maTaiKhoan") String maTaiKhoan,
            @RequestBody TaiKhoanUpdateRequest request
    ) {
        ApiResponse response = ApiResponse.builder()
                .code(202)
                .message("Update TaiKhoan successful")
                .data(taiKhoanService.updateTaiKhoan(maTaiKhoan, request))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{maTaiKhoan}")
    ResponseEntity<ApiResponse> deleteTaiKhoan(@PathVariable("maTaiKhoan") String maTaiKhoan) {
        taiKhoanService.deleteTaiKhoan(maTaiKhoan);
        ApiResponse response = ApiResponse.builder()
                .code(202)
                .message("Delete TaiKhoan successful")
                .build();
        return ResponseEntity.ok(response);
    }
}
