package com.example.CTDT_APP.Controller;

import com.example.CTDT_APP.DTO.Request.AuthenticationRequest;
import com.example.CTDT_APP.DTO.Request.IntrospectRequest;
import com.example.CTDT_APP.DTO.Respone.ApiRespone;
import com.example.CTDT_APP.DTO.Respone.AuthenticationRespone;
import com.example.CTDT_APP.DTO.Respone.IntrospectRespone;
import com.example.CTDT_APP.Entity.TaiKhoan;
import com.example.CTDT_APP.Service.AuthenticationService;
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
