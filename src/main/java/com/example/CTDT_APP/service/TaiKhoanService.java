package com.example.CTDT_APP.service;

import com.example.CTDT_APP.constant.TrangThai;
import com.example.CTDT_APP.dto.request.DoiMatKhauRequest;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Service
@Slf4j
public class TaiKhoanService {
    private final VaiTroRepository vaiTroRepository;
    private final AuthenticationManager authManager;
    private final TaiKhoanRepository taiKhoanRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public String register(TaiKhoanRegisterRequest req) {
        if (taiKhoanRepo.existsByTenDangNhap(req.getTenDangNhap())) throw new AppException("Tên đăng nhập đã tồn tại");

        TaiKhoan taiKhoan = TaiKhoan.builder()
                .tenDangNhap(req.getTenDangNhap())
                .matKhau(passwordEncoder.encode(req.getMatKhau()))
                .trangThai(req.getTrangThai())
                .vaiTro(vaiTroRepository.findByTenVaiTro("ROLE_EMPLOYEE").orElseThrow(() -> new AppException("Vai trò không tồn tại")))
                .trangThai(req.getTrangThai() == null ? TrangThai.HOAT_DONG : req.getTrangThai())
                .build();

        NhanVien nhanVien = NhanVien.builder()
                .hoTen(req.getHoVaTen())
                .email(req.getEmail())
                .soDienThoai(req.getSoDienThoai())
                .ngayThangNamSinh(req.getNgayThangNamSinh())
                .gioiTinh(req.getGioiTinh())
                .taiKhoan(taiKhoan)
                .build();

        taiKhoan.setNhanVien(nhanVien);

        return taiKhoanRepo.save(taiKhoan).getMaTaiKhoan();
    }

    public AuthRespone login(TaiKhoanLoginRequest req) {
        TaiKhoan taiKhoan = taiKhoanRepo.findByTenDangNhap(req.getTenDangNhap())
                .orElseThrow(() -> new AppException("Tên đăng nhập không tồn tại"));

        log.info("Paa: {}", passwordEncoder.encode("123456"));

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getTenDangNhap(),
                        req.getMatKhau()
                ));

        return AuthRespone.builder()
                .token(jwtService.generateToken(req.getTenDangNhap()))
                .role(taiKhoan.getVaiTro().getTenVaiTro())
                .build();
    }

    public void blockTaiKhoan(String maTaiKhoan) {
        TaiKhoan taiKhoan = taiKhoanRepo.findById(maTaiKhoan)
                .orElseThrow(() -> new AppException("Tài khoản không tồn tại"));
        taiKhoan.setTrangThai(TrangThai.NGUNG_HOAT_DONG);
        taiKhoanRepo.save(taiKhoan);
    }

    public void activeTaiKhoan(String maTaiKhoan) {
        TaiKhoan taiKhoan = taiKhoanRepo.findById(maTaiKhoan)
                .orElseThrow(() -> new AppException("Tài khoản không tồn tại"));
        taiKhoan.setTrangThai(TrangThai.HOAT_DONG);
        taiKhoanRepo.save(taiKhoan);
    }

    public TokenValidReponse isValidToken(@RequestParam String token) {
        return TokenValidReponse.builder()
                .isValidToken(jwtService.isTokenExpired(token))
                .build();
    }

    public void capMatKhau(String username, DoiMatKhauRequest req) {
        TaiKhoan taiKhoan = taiKhoanRepo.findByTenDangNhap(username)
                .orElseThrow(() -> new AppException("Tài khoản không tồn tại"));

//        if (!passwordEncoder.matches(req.getMatKhauCu(), taiKhoan.getMatKhau())) {
//            throw new AppException("Mật khẩu cũ không đúng");
//        }

        taiKhoan.setMatKhau(passwordEncoder.encode(req.getMatKhauMoi()));
        taiKhoanRepo.save(taiKhoan);
    }
    public void doiMatKhau(String id, DoiMatKhauRequest req) {
        TaiKhoan taiKhoan = taiKhoanRepo.findById(id)
                .orElseThrow(() -> new AppException("Tài khoản không tồn tại"));

        if (!passwordEncoder.matches(req.getMatKhauCu(), taiKhoan.getMatKhau())) {
            throw new AppException("Mật khẩu cũ không đúng");
        }

        taiKhoan.setMatKhau(passwordEncoder.encode(req.getMatKhauMoi()));
        taiKhoanRepo.save(taiKhoan);
    }
}
