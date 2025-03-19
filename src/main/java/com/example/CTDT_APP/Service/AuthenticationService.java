package com.example.CTDT_APP.Service;

import com.example.CTDT_APP.DTO.Request.AuthenticationRequest;
import com.example.CTDT_APP.Repository.TaiKhoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    TaiKhoanRepository taiKhoanRepository;
    public boolean authenticate(AuthenticationRequest request){
        var taikhoan = taiKhoanRepository.findByTenDangNhap(request.getTenDangNhap()).orElseThrow(()-> new RuntimeException("Tai khoan khong ton tai"));
        PasswordEncoder passwordEncoder= new BCryptPasswordEncoder(10);
        return passwordEncoder.matches(request.getMatKhau(),taikhoan.getMatKhau());
    }
}
