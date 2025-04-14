package com.example.CTDT_APP.service;

import com.example.CTDT_APP.constant.TrangThai;
import com.example.CTDT_APP.dto.request.ChuongTrinhDaoTaoCreationRequest;
import com.example.CTDT_APP.dto.request.ChuongTrinhDaoTaoUpdateRequest;
import com.example.CTDT_APP.dto.response.ChuongTrinhDaoTaoResponse;
import com.example.CTDT_APP.dto.response.NamCTDTResponse;
import com.example.CTDT_APP.entity.BacDaoTao;
import com.example.CTDT_APP.entity.ChuongTrinhDaoTao;
import com.example.CTDT_APP.entity.HeDaoTao;
import com.example.CTDT_APP.entity.NganhDaoTao;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.BacDaoTaoRepository;
import com.example.CTDT_APP.repository.ChuongTrinhDaoTaoRepository;
import com.example.CTDT_APP.repository.HeDaoTaoRepository;
import com.example.CTDT_APP.repository.NganhDaoTaoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChuongTrinhDaoTaoService {

    private final ChuongTrinhDaoTaoRepository ctdtRepo;
    private final BacDaoTaoRepository bacDaoTaoRepo;
    private final HeDaoTaoRepository heDaoTaoRepo;
    private final NganhDaoTaoRepository nganhDaoTaoRepo;
    private final ModelMapper mapper;

    public List<ChuongTrinhDaoTaoResponse> getAllCTDT() {
        return ctdtRepo.findAll().stream().map(ctdt -> {
            // Map các trường cơ bản của CTDT
            ChuongTrinhDaoTaoResponse response = mapper.map(ctdt, ChuongTrinhDaoTaoResponse.class);

            // Map danh sách Nam_CTDT từ entity NamCTDT sang DTO NamCTDTResponse
            if(ctdt.getNamCtDtList() != null) {
                List<NamCTDTResponse> namResponses = ctdt.getNamCtDtList().stream()
                        .map(nctdt -> NamCTDTResponse.builder()
                                .nam(nctdt.getId().getNam())
                                .maCTDT(nctdt.getId().getMaCTDT())
                                .build())
                        .collect(Collectors.toList());
                response.setNamCtDtList(namResponses);
            }
            return response;
        }).collect(Collectors.toList());
    }

    public ChuongTrinhDaoTaoResponse createCTDT(ChuongTrinhDaoTaoCreationRequest req) {
        if (ctdtRepo.existsById(req.getMaCTDT())) {
            throw new AppException("Chương trình đào tạo đã tồn tại");
        }
        // Tra cứu các khóa ngoại
        BacDaoTao bacDaoTao = bacDaoTaoRepo.findById(req.getMaBac())
                .orElseThrow(() -> new AppException("Không tìm thấy bậc đào tạo"));
        HeDaoTao heDaoTao = heDaoTaoRepo.findById(req.getMaHe())
                .orElseThrow(() -> new AppException("Không tìm thấy hình thức đào tạo"));
        NganhDaoTao nganhDaoTao = nganhDaoTaoRepo.findById(req.getMaNganh())
                .orElseThrow(() -> new AppException("Không tìm thấy ngành đào tạo"));

        ChuongTrinhDaoTao ctdt = mapper.map(req, ChuongTrinhDaoTao.class);
        // Sử dụng trực tiếp TrangThai.valueOf vì enum cố định
        ctdt.setTrangThai(TrangThai.valueOf(req.getTrangThai().toUpperCase().replace(" ", "_")));
        ctdt.setBacDaoTao(bacDaoTao);
        ctdt.setHeDaoTao(heDaoTao);
        ctdt.setNganhDaoTao(nganhDaoTao);

        ChuongTrinhDaoTao saved = ctdtRepo.save(ctdt);
        return mapper.map(saved, ChuongTrinhDaoTaoResponse.class);
    }

    public ChuongTrinhDaoTaoResponse updateCTDT(String maCTDT, ChuongTrinhDaoTaoUpdateRequest req) {
        ChuongTrinhDaoTao ctdt = ctdtRepo.findById(maCTDT)
                .orElseThrow(() -> new AppException("Không tìm thấy chương trình đào tạo"));

        BacDaoTao bacDaoTao = bacDaoTaoRepo.findById(req.getMaBac())
                .orElseThrow(() -> new AppException("Không tìm thấy bậc đào tạo"));
        HeDaoTao heDaoTao = heDaoTaoRepo.findById(req.getMaHe())
                .orElseThrow(() -> new AppException("Không tìm thấy hình thức đào tạo"));
        NganhDaoTao nganhDaoTao = nganhDaoTaoRepo.findById(req.getMaNganh())
                .orElseThrow(() -> new AppException("Không tìm thấy ngành đào tạo"));

        mapper.map(req, ctdt);
        ctdt.setTrangThai(TrangThai.valueOf(req.getTrangThai().toUpperCase().replace(" ", "_")));
        ctdt.setBacDaoTao(bacDaoTao);
        ctdt.setHeDaoTao(heDaoTao);
        ctdt.setNganhDaoTao(nganhDaoTao);

        ChuongTrinhDaoTao updated = ctdtRepo.save(ctdt);
        return mapper.map(updated, ChuongTrinhDaoTaoResponse.class);
    }


    public void deleteCTDT(String maCTDT) {
        if (!ctdtRepo.existsById(maCTDT)) {
            throw new AppException("Không tìm thấy chương trình đào tạo");
        }
        ctdtRepo.deleteById(maCTDT);
    }

    public ChuongTrinhDaoTaoResponse findCTDT(String maCTDT) {
        ChuongTrinhDaoTao ctdt = ctdtRepo.findById(maCTDT)
                .orElseThrow(() -> new AppException("Không tìm thấy chương trình đào tạo"));
        return mapper.map(ctdt, ChuongTrinhDaoTaoResponse.class);
    }
}
