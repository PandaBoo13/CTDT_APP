package com.example.CTDT_APP.service;

import com.example.CTDT_APP.entity.NhanVienResponse;
import com.example.CTDT_APP.repository.NhanVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NhanVienService {
    private final NhanVienRepository nhanVienRepository;

    public List<NhanVienResponse> getAllNhanVien() {
        return nhanVienRepository.findAll().stream()
                .map(nhanVien ->
                        NhanVienResponse
                                .builder()
                                .maTaiKhoan(nhanVien.getTaiKhoan().getMaTaiKhoan())
                                .maNV(nhanVien.getMaNhanVien())
                                .hoTen(nhanVien.getHoTen())
                                .email(nhanVien.getEmail())
                                .soDienThoai(nhanVien.getSoDienThoai())
                                .ngayThangNamSinh(nhanVien.getNgayThangNamSinh())
                                .gioiTinh(nhanVien.getGioiTinh().getValue())
                                .trangThai(nhanVien.getTaiKhoan().getTrangThai().getValue())
                                .build())
                .toList();
    }
}
