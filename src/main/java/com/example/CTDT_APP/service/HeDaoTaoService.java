package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.HeDaoTaoCreationRequest;
import com.example.CTDT_APP.dto.request.HeDaoTaoUpdateRequest;
import com.example.CTDT_APP.entity.HeDaoTao;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.HeDaoTaoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HeDaoTaoService {
    private final HeDaoTaoRepository heDaoTaoRepository;
    private final ModelMapper mapper;

    public List<HeDaoTao> getAllHeDaoTao() {
        return heDaoTaoRepository.findAll();
    }

    public HeDaoTao createHeDaoTao(HeDaoTaoCreationRequest req) {
        if (heDaoTaoRepository.existsById(req.getMaHe())) {
            throw new AppException("Mã hệ đào tạo đã tồn tại");
        }
        return heDaoTaoRepository.save(mapper.map(req, HeDaoTao.class));
    }

    public HeDaoTao updateHeDaoTao(String maHeDaoTao, HeDaoTaoUpdateRequest req) {
        HeDaoTao heDaoTao = heDaoTaoRepository.findById(maHeDaoTao)
                .orElseThrow(() -> new AppException("Không tìm thấy hệ đào tạo"));

        mapper.map(req, heDaoTao);

        return heDaoTaoRepository.save(heDaoTao);
    }

    public void deleteHeDaoTao(String maHeDaoTao) {
        if (!heDaoTaoRepository.existsById(maHeDaoTao)) {
            throw new AppException("Không tìm thấy hệ đào tạo");
        }
        heDaoTaoRepository.deleteById(maHeDaoTao);
    }
}
