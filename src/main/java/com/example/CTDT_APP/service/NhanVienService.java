package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.NhanVienUpdateRequest;
import com.example.CTDT_APP.entity.NhanVien;
import com.example.CTDT_APP.entity.NhanVienResponse;
import com.example.CTDT_APP.entity.TaiKhoan;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.NhanVienRepository;
import com.example.CTDT_APP.repository.TaiKhoanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NhanVienService {
    private final NhanVienRepository nhanVienRepository;
    private final TaiKhoanRepository taiKhoanRepository;
    private final ModelMapper modelMapper;

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

    @Transactional
    public NhanVienResponse updateNhanVien(String id, NhanVienUpdateRequest request) {
        NhanVien updateNhanVien = nhanVienRepository.findById(id)
                .orElseThrow(() -> new AppException("Không tìm thấy nhân viên"));
        modelMapper.map(request, updateNhanVien);

        TaiKhoan taiKhoan = updateNhanVien.getTaiKhoan();
        taiKhoan.setTrangThai(request.getTrangThai());
        taiKhoanRepository.save(taiKhoan);

        return modelMapper.map(nhanVienRepository.save(updateNhanVien), NhanVienResponse.class);
    }
}
