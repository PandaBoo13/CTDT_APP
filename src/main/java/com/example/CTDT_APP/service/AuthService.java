package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.AuthRequest;
import com.example.CTDT_APP.dto.response.AuthRespone;
import com.example.CTDT_APP.dto.response.TokenValidReponse;
import com.example.CTDT_APP.entity.TaiKhoan;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.TaiKhoanRepository;
import com.example.CTDT_APP.repository.VaiTroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final TaiKhoanRepository taiKhoanRepo;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;
    private final VaiTroRepository vaiTroRepo;

    public AuthRespone login(AuthRequest request) {
        if (!taiKhoanRepo.existsByTenDangNhap(request.getTenDangNhap()))
            throw new AppException("Tai khoan khong ton tai");
        if (!authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getTenDangNhap(),
                        request.getMatKhau()
                )).isAuthenticated()) {
            throw new AppException("Sai mat khau");
        }
        return AuthRespone.builder()
                .token(jwtService.generateToken(request.getTenDangNhap()))
                .build();
    }

    @Transactional
    public String register(AuthRequest request) {
        if (taiKhoanRepo.existsByTenDangNhap(request.getTenDangNhap()))
            throw new AppException("Tai khoan da ton tai");

        TaiKhoan taiKhoan = TaiKhoan.builder()
                .tenDangNhap(request.getTenDangNhap())
                .matKhau(passwordEncoder.encode(request.getMatKhau()))
                .vaiTro(vaiTroRepo.findById("EMPLOYEE")
                        .orElseThrow(() -> new AppException("Vai tro khong ton tai")))
                .build();
        return taiKhoanRepo.save(taiKhoan).getMaTaiKhoan();
    }

    public TokenValidReponse isValidToken(String token) {
        return TokenValidReponse.builder()
                .isValidToken(jwtService.isTokenExpired(token))
                .build();
    }
}
