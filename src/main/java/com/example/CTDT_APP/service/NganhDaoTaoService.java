package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.NganhDaoTaoCreationRequest;
import com.example.CTDT_APP.dto.request.NganhDaoTaoUpdateRequest;
import com.example.CTDT_APP.entity.Khoa;
import com.example.CTDT_APP.entity.NganhDaoTao;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.KhoaRepository;
import com.example.CTDT_APP.repository.NganhDaoTaoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NganhDaoTaoService {
    private final NganhDaoTaoRepository nganhDaoTaoRepo;
    private final KhoaRepository khoaRepo;
    private final ModelMapper mapper;

    public NganhDaoTao createNganhDaoTao(NganhDaoTaoCreationRequest req) {
        if (nganhDaoTaoRepo.existsById(req.getMaNganh())) throw new AppException("Ngành đào tạo đã tồn tại");

        Khoa khoa = khoaRepo.findById(req.getMaKhoa())
                .orElseThrow(() -> new AppException("Khoa không tồn tại"));

        NganhDaoTao nganhDaoTao = mapper.map(req, NganhDaoTao.class);
        nganhDaoTao.setKhoa(khoa);

        return nganhDaoTaoRepo.save(nganhDaoTao);
    }

    public NganhDaoTao updateNganhDaoTao(String maNganhDaoTao, NganhDaoTaoUpdateRequest req) {
        NganhDaoTao nganhDaoTao = nganhDaoTaoRepo.findById(maNganhDaoTao)
                .orElseThrow(() -> new AppException("Ngành đào tạo không tồn tại"));
        Khoa khoa = khoaRepo.findById(req.getMaKhoa())
                .orElseThrow(() -> new AppException("Khoa không tồn tại"));

        mapper.map(req, nganhDaoTao);
        nganhDaoTao.setKhoa(khoa);

        return nganhDaoTaoRepo.save(nganhDaoTao);
    }

    public List<NganhDaoTao> getAllNganhDaoTao() {
        return nganhDaoTaoRepo.findAll();
    }

    public void deleteNganhDaoTao(String maNganhDaoTao) {
        if (!nganhDaoTaoRepo.existsById(maNganhDaoTao)) throw new AppException("Ngành đào tạo không tồn tại");
        try {
            nganhDaoTaoRepo.deleteById(maNganhDaoTao);
        } catch(DataIntegrityViolationException e){
            if(e.getRootCause() instanceof SQLIntegrityConstraintViolationException){
                throw new AppException("Không thể xóa vì đã có chuyên ngành hoặc kế hoạch đào tạo tham chiếu đến ngành này");
            }
        }
    }
}
