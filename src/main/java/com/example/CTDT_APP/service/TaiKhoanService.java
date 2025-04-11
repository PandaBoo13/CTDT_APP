package com.example.CTDT_APP.service;

import com.example.CTDT_APP.constant.TrangThai;
import com.example.CTDT_APP.dto.request.TaiKhoanLoginRequest;
import com.example.CTDT_APP.dto.request.TaiKhoanRegisterRequest;
import com.example.CTDT_APP.dto.response.AuthRespone;
import com.example.CTDT_APP.dto.response.TokenValidReponse;
import com.example.CTDT_APP.entity.NhanVien;
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
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Service
public class TaiKhoanService {
    private final VaiTroRepository vaiTroRepository;
    private final AuthenticationManager authManager;
    private final TaiKhoanRepository taiKhoanRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public String register(TaiKhoanRegisterRequest req) {
        if (taiKhoanRepo.existsByTenDangNhap(req.getTenDangNhap()))
            throw new AppException("Tên đăng nhập đã tồn tại");

        NhanVien nhanVien = NhanVien.builder()
                .hoTen(req.getHoVaTen())
                .email(req.getEmail())
                .soDienThoai(req.getSoDienThoai())
                .ngayThangNamSinh(req.getNgayThangNamSinh())
                .gioiTinh(req.getGioiTinh())
                .build();

        TaiKhoan taiKhoan = TaiKhoan.builder()
                .tenDangNhap(req.getTenDangNhap())
                .matKhau(passwordEncoder.encode(req.getMatKhau()))
                .trangThai(req.getTrangThai())
                .vaiTro(vaiTroRepository.findByTenVaiTro("ROLE_EMPLOYEE").orElseThrow(() -> new AppException("Vai trò không tồn tại")))
                .nhanVien(nhanVien)
                .build();

        return taiKhoanRepo.save(taiKhoan).getMaTaiKhoan();
    }

    public AuthRespone login(TaiKhoanLoginRequest req) {
        TaiKhoan taiKhoan = taiKhoanRepo.findByTenDangNhap(req.getTenDangNhap())
                .orElseThrow(() -> new AppException("Tên đăng nhập không tồn tại"));

        if (!authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getTenDangNhap(),
                        req.getMatKhau()
                )).isAuthenticated()) {
            throw new AppException("Sai mật khẩu");
        }

        return AuthRespone.builder()
                .token(jwtService.generateToken(req.getTenDangNhap()))
                .role(taiKhoan.getVaiTro().getTenVaiTro())
                .build();
    }

    public void blockTaiKhoan(String maTaiKhoan) {
        TaiKhoan taiKhoan = taiKhoanRepo.findById(maTaiKhoan)
                .orElseThrow(() -> new AppException("Tài khoản không tồn tại"));
        taiKhoan.setTrangThai(TrangThai.INACTIVE);
        taiKhoanRepo.save(taiKhoan);
    }

    public TokenValidReponse isValidToken(@RequestParam String token) {
        return TokenValidReponse.builder()
                .isValidToken(jwtService.isTokenExpired(token))
                .build();
    }
}
