package com.example.CTDT_APP.Controller;

import com.example.CTDT_APP.DTO.Request.AuthenticationRequest;
import com.example.CTDT_APP.Entity.TaiKhoan;
import com.example.CTDT_APP.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    Boolean authenticate (@RequestBody AuthenticationRequest request){
      boolean result=   authenticationService.authenticate(request);
      return  result;
    }

}
