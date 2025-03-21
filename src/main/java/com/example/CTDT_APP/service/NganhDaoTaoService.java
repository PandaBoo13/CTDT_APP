package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.NDTCreationRequest;
import com.example.CTDT_APP.dto.request.NDTUpdateRequest;
import com.example.CTDT_APP.entity.Khoa;
import com.example.CTDT_APP.entity.NganhDaoTao;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.KhoaRepository;
import com.example.CTDT_APP.repository.NganhDaoTaoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NganhDaoTaoService {
    private final NganhDaoTaoRepository nganhDaoTaoRepo;
    private final KhoaRepository khoaRepo;
    private final ModelMapper mapper;

    public NganhDaoTao createNganhDaoTao(NDTCreationRequest req) {
        if (nganhDaoTaoRepo.existsById(req.getMaNganh())) throw new AppException("Nganh dao tao da ton tai");

        Khoa khoa = khoaRepo.findById(req.getMaKhoa())
                .orElseThrow(() -> new AppException("Khoa khong ton tai"));

        NganhDaoTao nganhDaoTao = mapper.map(req, NganhDaoTao.class);
        nganhDaoTao.setKhoa(khoa);

        return nganhDaoTaoRepo.save(nganhDaoTao);
    }

    public NganhDaoTao updateNganhDaoTao(String maNganhDaoTao, NDTUpdateRequest req) {
        NganhDaoTao nganhDaoTao = nganhDaoTaoRepo.findById(maNganhDaoTao)
                .orElseThrow(() -> new AppException("Nganh dao tao khong ton tai"));
        Khoa khoa = khoaRepo.findById(req.getMaKhoa())
                .orElseThrow(() -> new AppException("Khoa khong ton tai"));

        mapper.map(req, nganhDaoTao);
        nganhDaoTao.setKhoa(khoa);

        return nganhDaoTaoRepo.save(nganhDaoTao);
    }

    public List<NganhDaoTao> getAllNganhDaoTao() {
        return nganhDaoTaoRepo.findAll();
    }

    public void deleteNganhDaoTao(String maNganhDaoTao) {
        if (!nganhDaoTaoRepo.existsById(maNganhDaoTao)) {
            throw new AppException("Nganh dao tao khong ton tai");
        }
        nganhDaoTaoRepo.deleteById(maNganhDaoTao);
    }
}
