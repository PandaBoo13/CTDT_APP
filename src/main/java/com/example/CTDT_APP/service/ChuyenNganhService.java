package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.ChuyenNganhCreationRequest;
import com.example.CTDT_APP.dto.request.ChuyenNganhUpdateRequest;
import com.example.CTDT_APP.entity.ChuyenNganh;
import com.example.CTDT_APP.entity.NganhDaoTao;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.ChuyenNganhRepository;
import com.example.CTDT_APP.repository.NganhDaoTaoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChuyenNganhService {

    private final ChuyenNganhRepository chuyenNganhRepo;
    private final NganhDaoTaoRepository nganhDaoTaoRepository;
    private final ModelMapper mapper;

    public List<ChuyenNganh> getAllChuyenNganh() {
        return chuyenNganhRepo.findAll();
    }

    public ChuyenNganh createChuyenNganh(ChuyenNganhCreationRequest req) {
        if (chuyenNganhRepo.existsById(req.getMaChuyenNganh())) {
            throw new AppException("Chuyen nganh da ton tai");
        }

        NganhDaoTao nganhDaoTao = nganhDaoTaoRepository.findById(req.getMaNganh())
                .orElseThrow(() -> new AppException("Khong tim thay nganh dao tao"));

        ChuyenNganh chuyenNganh = mapper.map(req, ChuyenNganh.class);
        chuyenNganh.setMaNganh(nganhDaoTao);

        return chuyenNganhRepo.save(chuyenNganh);
    }

    public ChuyenNganh updateChuyenNganh(String maChuyenNganh, ChuyenNganhUpdateRequest req) {
        NganhDaoTao nganhDaoTao = nganhDaoTaoRepository.findById(req.getMaNganh())
                .orElseThrow(() -> new AppException("Khong tim thay nganh dao tao"));

        ChuyenNganh chuyenNganh = chuyenNganhRepo.findById(maChuyenNganh)
                .orElseThrow(() -> new AppException("Khong tim thay chuyen nganh"));

        mapper.map(req, chuyenNganh);
        chuyenNganh.setMaNganh(nganhDaoTao);

        return chuyenNganhRepo.save(chuyenNganh);
    }

    public void deleteChuyenNganh(String maChuyenNganh) {
        if (!chuyenNganhRepo.existsById(maChuyenNganh)) {
            throw new AppException("Khong tim thay chuyen nganh");
        }
        chuyenNganhRepo.deleteById(maChuyenNganh);
    }
}
