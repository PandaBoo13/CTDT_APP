package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.BacDaoTaoCreationRequest;
import com.example.CTDT_APP.dto.request.BacDaoTaoUpdateRequest;
import com.example.CTDT_APP.entity.BacDaoTao;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.BacDaoTaoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class    BacDaoTaoService {
    private final BacDaoTaoRepository bacDaoTaoRepository;
    private final ModelMapper mapper;

    public List<BacDaoTao> getAllBacDaoTao() {
        return bacDaoTaoRepository.findAll();
    }

    public BacDaoTao createBacDaoTao(BacDaoTaoCreationRequest req) {
        if (bacDaoTaoRepository.existsById(req.getMaBac())) {
            throw new AppException("Mã bậc đào tạo đã tồn tại");
        }

        return bacDaoTaoRepository.save(mapper.map(req, BacDaoTao.class));
    }

    public BacDaoTao updateBacDaoTao(String maBacDaoTao, BacDaoTaoUpdateRequest req) {
        BacDaoTao bacDaoTao = bacDaoTaoRepository.findById(maBacDaoTao)
                .orElseThrow(() -> new AppException("Không tìm thấy bậc đào tạo"));

        mapper.map(req, bacDaoTao);

        return bacDaoTaoRepository.save(bacDaoTao);
    }

    public void deleteBacDaoTao(String maBacDaoTao) {
        if (!bacDaoTaoRepository.existsById(maBacDaoTao)) {
            throw new AppException("Không tìm thấy bậc đào tạo");
        }
        bacDaoTaoRepository.deleteById(maBacDaoTao);
    }
}