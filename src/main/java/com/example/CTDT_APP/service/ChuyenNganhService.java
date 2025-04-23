package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.ChuyenNganhCreationRequest;
import com.example.CTDT_APP.dto.request.ChuyenNganhUpdateRequest;
import com.example.CTDT_APP.dto.response.ChuyenNganhReponse;
import com.example.CTDT_APP.entity.ChuyenNganh;
import com.example.CTDT_APP.entity.NganhDaoTao;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.ChuyenNganhRepository;
import com.example.CTDT_APP.repository.NganhDaoTaoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChuyenNganhService {

    private final ChuyenNganhRepository chuyenNganhRepo;
    private final NganhDaoTaoRepository nganhDaoTaoRepository;
    private final ModelMapper mapper;

    public List<ChuyenNganhReponse> getAllChuyenNganh() {
        return chuyenNganhRepo.findAll()
                .stream()
                .map(chuyenNganh -> mapper.map(chuyenNganh, ChuyenNganhReponse.class))
                .toList();
    }

    public ChuyenNganhReponse createChuyenNganh(ChuyenNganhCreationRequest req) {
        if (chuyenNganhRepo.existsById(req.getMaChuyenNganh())) throw new AppException("Chuyên ngành đã tồn tại");

        NganhDaoTao nganhDaoTao = nganhDaoTaoRepository.findById(req.getMaNganh())
                .orElseThrow(() -> new AppException("Không tìm thấy ngành đào tạo"));

        ChuyenNganh chuyenNganh = mapper.map(req, ChuyenNganh.class);
        chuyenNganh.setNganhDaoTao(nganhDaoTao);

        return mapper.map(chuyenNganhRepo.save(chuyenNganh), ChuyenNganhReponse.class);
    }

    public ChuyenNganhReponse updateChuyenNganh(String maChuyenNganh, ChuyenNganhUpdateRequest req) {
        NganhDaoTao nganhDaoTao = nganhDaoTaoRepository.findById(req.getMaNganh())
                .orElseThrow(() -> new AppException("Không tìm thấy ngành đào tạo"));

        ChuyenNganh chuyenNganh = chuyenNganhRepo.findById(maChuyenNganh)
                .orElseThrow(() -> new AppException("Không tìm thấy chuyên ngành"));

        mapper.map(req, chuyenNganh);
        chuyenNganh.setNganhDaoTao(nganhDaoTao);

        return mapper.map(chuyenNganhRepo.save(chuyenNganh), ChuyenNganhReponse.class);
    }

    public void deleteChuyenNganh(String maChuyenNganh) {
        if (!chuyenNganhRepo.existsById(maChuyenNganh)) throw new AppException("Không tìm thấy chuyên ngành");
        try {
            chuyenNganhRepo.deleteById(maChuyenNganh);
        } catch(DataIntegrityViolationException e){
            if(e.getRootCause() instanceof SQLIntegrityConstraintViolationException){
                throw new AppException("Không thể xóa vì đã có chương trình đào tạo tham chiếu đến chuyên ngành này");
            }
        }
    }
}
