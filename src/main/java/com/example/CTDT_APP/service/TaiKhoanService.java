package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.TaiKhoanCreationRequest;
import com.example.CTDT_APP.dto.request.TaiKhoanUpdateRequest;
import com.example.CTDT_APP.entity.TaiKhoan;
import com.example.CTDT_APP.entity.VaiTro;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.mapper.TaiKhoanMapper;
import com.example.CTDT_APP.repository.TaiKhoanRepository;
import com.example.CTDT_APP.repository.VaiTroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaiKhoanService {
    @Autowired
    private VaiTroRepository vaiTroRepository;
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;
    @Autowired
    private ModelMapper taiKhoanMapper;

    public TaiKhoan createTaikhoan(TaiKhoanCreationRequest request) {
        TaiKhoan taiKhoan = taiKhoanMapper.map(request,TaiKhoan.class);
        if (taiKhoanRepository.existsByTenDangNhap(request.getTenDangNhap()))
            throw new AppException("Ten dang nhap da ton tai");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        taiKhoan.setMatKhau(passwordEncoder.encode(request.getMatKhau()));
        VaiTro vaiTroMacDinh = vaiTroRepository.findById("EMPLOYEE")
                .orElseThrow(() -> new RuntimeException("Không tìm thấy vai trò EMPLOYEE"));

        taiKhoan.setVaiTro(vaiTroMacDinh);
        return taiKhoanRepository.save(taiKhoan);
    }

    public List<TaiKhoan> getTaiKhoan() {
        return taiKhoanRepository.findAll();
    }


    public TaiKhoan getTaiKhoanById(String id) {
        return taiKhoanRepository.findById(id)
                .orElseThrow(() -> new AppException("Tai khoan khong ton tai"));
    }


    public TaiKhoan updateTaiKhoan(String maTaiKhoan, TaiKhoanUpdateRequest request) {
        TaiKhoan taiKhoan = getTaiKhoanById(maTaiKhoan);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        taiKhoan.setMatKhau(passwordEncoder.encode(request.getMatKhau()));
        return taiKhoanRepository.save(taiKhoan);
    }

    public void deleteTaiKhoan(String maTaiKhoan) {
        taiKhoanRepository.deleteById(maTaiKhoan);
    }


}
