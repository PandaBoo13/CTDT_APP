package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.request.AuthRequest;
import com.example.CTDT_APP.dto.request.TokenValidationRequest;
import com.example.CTDT_APP.dto.response.ApiResponse;
import com.example.CTDT_APP.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    ResponseEntity<ApiResponse> login(@RequestBody AuthRequest request) {
        ApiResponse response = ApiResponse.builder()
                .code(202)
                .message("Dang nhap thanh cong")
                .data(authService.login(request))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    ResponseEntity<ApiResponse> register(@RequestBody AuthRequest request) {
        ApiResponse response = ApiResponse.builder()
                .code(201)
                .message("Dang ky thanh cong")
                .data(authService.register(request))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate")
    ResponseEntity<ApiResponse> validate(@RequestBody TokenValidationRequest request) {
        ApiResponse response = ApiResponse.builder()
                .code(202)
                .message("Token hop le")
                .data(authService.isValidToken(request.getToken()))
                .build();
        return ResponseEntity.ok(response);
    }
}
