package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.request.AuthenticationRequest;
import com.example.CTDT_APP.dto.request.IntrospectRequest;
import com.example.CTDT_APP.dto.response.ApiRespone;
import com.example.CTDT_APP.dto.response.AuthenticationRespone;
import com.example.CTDT_APP.dto.response.IntrospectRespone;
import com.example.CTDT_APP.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiRespone<AuthenticationRespone> authenticate (@RequestBody AuthenticationRequest request){
        var result=authenticationService.authenticate(request);
        return  ApiRespone.<AuthenticationRespone>builder()
                .result(result)
                .build();

    }

    @PostMapping("/introspect")
    ApiRespone<IntrospectRespone> authenticate (@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result=authenticationService.introspectRespone(request);
        return  ApiRespone.<IntrospectRespone>builder()
                .result(result)
                .build();

    }



}
