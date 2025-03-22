package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.TaiKhoanCreationRequest;
import com.example.CTDT_APP.dto.request.TaiKhoanUpdateRequest;
import com.example.CTDT_APP.entity.TaiKhoan;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.mapper.TaiKhoanMapper;
import com.example.CTDT_APP.repository.TaiKhoanRepository;
import com.example.CTDT_APP.repository.VaiTroRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private TaiKhoanMapper taiKhoanMapper;
    public TaiKhoan createTaikhoan(TaiKhoanCreationRequest request){
        TaiKhoan taiKhoan= taiKhoanMapper.toTaiKhoan(request);
        if (taiKhoanRepository.existsByMaTaiKhoan(request.getMaTaiKhoan()))
            throw new AppException("Tai khoan da ton tai");
        if (taiKhoanRepository.existsByTenDangNhap(request.getTenDangNhap()))
            throw new AppException("Ten dang nhap da ton tai");
        PasswordEncoder passwordEncoder= new BCryptPasswordEncoder(10);
        taiKhoan.setMatKhau(passwordEncoder.encode(request.getMatKhau()));

        return taiKhoanRepository.save(taiKhoan);
    }
    public List<TaiKhoan> getTaiKhoan(){
        return taiKhoanRepository.findAll();
    }

    public TaiKhoan getTaiKhoanById( String Id){
        return taiKhoanRepository.findById(Id).orElseThrow(() -> new AppException(" Tai khoan khong ton tai"));
    }

    public TaiKhoan updateTaiKhoan(String maTaiKhoan, TaiKhoanUpdateRequest request){
        TaiKhoan taiKhoan= getTaiKhoanById(maTaiKhoan);
        PasswordEncoder passwordEncoder= new BCryptPasswordEncoder(10);
        taiKhoan.setMatKhau(passwordEncoder.encode(request.getMatKhau()));
        return taiKhoanRepository.save(taiKhoan);
    }

    public void deleteTaiKhoan(String maTaiKhoan){
        taiKhoanRepository.deleteById(maTaiKhoan);
    }


}
