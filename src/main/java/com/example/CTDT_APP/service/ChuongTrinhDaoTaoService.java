package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.ChuongTrinhDaoTaoCreationRequest;
import com.example.CTDT_APP.entity.*;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.BacDaoTaoRepository;
import com.example.CTDT_APP.repository.ChuongTrinhDaoTaoRepository;
import com.example.CTDT_APP.repository.HeDaoTaoRepository;
import com.example.CTDT_APP.repository.NganhDaoTaoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChuongTrinhDaoTaoService {
    private final ChuongTrinhDaoTaoRepository ctdtRepo;
    private final HeDaoTaoRepository heDaoTaoRepo;
    private final BacDaoTaoRepository bacDaoTaoRepo;
    private final NganhDaoTaoRepository nganhDaoTaoRepo;
    private final ModelMapper mapper;

    public ChuongTrinhDaoTao createCTDT(ChuongTrinhDaoTaoCreationRequest req) {
        HeDaoTao heDaoTao = heDaoTaoRepo.findById(req.getMaHe())
                .orElseThrow(() -> new AppException("Không tìm thấy hệ đào tạo"));
        BacDaoTao bacDaoTao = bacDaoTaoRepo.findById(req.getCapBac())
                .orElseThrow(() -> new AppException("Không tìm thấy bậc đào tạo"));
        NganhDaoTao nganhDaoTao = nganhDaoTaoRepo.findById(req.getMaNganh())
                .orElseThrow(() -> new AppException("Không tìm thấy chuyên ngành"));

        ChuongTrinhDaoTao chuongTrinhDaoTao = mapper.map(req, ChuongTrinhDaoTao.class);
        chuongTrinhDaoTao.setHeDaoTao(heDaoTao);
        chuongTrinhDaoTao.setNganhDaoTao(nganhDaoTao);
        chuongTrinhDaoTao.setBacDaoTao(bacDaoTao);

        chuongTrinhDaoTao.getNamDaoTaos().addAll(
                req.getNamDaoTao().stream()
                        .filter(Objects::nonNull)
                        .map(nam -> NamDaoTao.builder().nam(nam).build())
                        .collect(Collectors.toSet())
        );

        return ctdtRepo.save(chuongTrinhDaoTao);
    }
}
