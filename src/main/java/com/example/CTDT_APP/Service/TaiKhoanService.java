package com.example.CTDT_APP.Service;

import com.example.CTDT_APP.DTO.Request.TaiKhoanCreationRequest;
import com.example.CTDT_APP.DTO.Request.TaiKhoanUpdateRequest;
import com.example.CTDT_APP.Entity.TaiKhoan;
import com.example.CTDT_APP.Mapper.TaiKhoanMapper;
import com.example.CTDT_APP.Repository.TaiKhoanRepository;
import com.example.CTDT_APP.Repository.VaiTroRepository;
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
        if(taiKhoanRepository.existsByMaTaiKhoan(request.getMaTaiKhoan())) throw new RuntimeException("Ma tai khoan da ton tai");
        if(taiKhoanRepository.existsByTenDangNhap(request.getTenDangNhap())) throw new RuntimeException("Ten dang nhap da ton tai");
        PasswordEncoder passwordEncoder= new BCryptPasswordEncoder(10);
        taiKhoan.setMatKhau(passwordEncoder.encode(request.getMatKhau()));

        return taiKhoanRepository.save(taiKhoan);
    }
    public List<TaiKhoan> getTaiKhoan(){
        return taiKhoanRepository.findAll();
    }

    public TaiKhoan getTaiKhoanById( String Id){
        return taiKhoanRepository.findById(Id).orElseThrow(() -> new RuntimeException(" Tai khoan khong ton tai"));
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
