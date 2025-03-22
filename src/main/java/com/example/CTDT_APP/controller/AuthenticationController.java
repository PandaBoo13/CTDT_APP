package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.request.AuthenticationRequest;
import com.example.CTDT_APP.dto.request.IntrospectRequest;
import com.example.CTDT_APP.dto.response.ApiResponse;
import com.example.CTDT_APP.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/token")
    ResponseEntity<ApiResponse> authenticate(@RequestBody AuthenticationRequest request) {
        ApiResponse response = ApiResponse.builder()
                .code(202)
                .message("Authentication successful")
                .data(authenticationService.authenticate(request))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/introspect")
    ResponseEntity<ApiResponse> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        ApiResponse response = ApiResponse.builder()
                .code(202)
                .message("???")
                .data(authenticationService.introspectRespone(request))
                .build();
        return ResponseEntity.ok(response);
    }
}
