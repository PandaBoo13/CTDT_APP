package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.ChuongTrinhDaoTaoCreationRequest;
import com.example.CTDT_APP.dto.request.ChuongTrinhDaoTaoUpdateRequest;
import com.example.CTDT_APP.dto.response.BacDaoTaoResponse;
import com.example.CTDT_APP.dto.response.ChuongTrinhDaoTaoResponse;
import com.example.CTDT_APP.dto.response.HeDaoTaoResponse;
import com.example.CTDT_APP.dto.response.NganhDaoTaoResponse;
import com.example.CTDT_APP.entity.*;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChuongTrinhDaoTaoService {

    private final ChuongTrinhDaoTaoRepository ctdtRepo;
    private final BacDaoTaoRepository bacDaoTaoRepo;
    private final HeDaoTaoRepository heDaoTaoRepo;
    private final NganhDaoTaoRepository nganhDaoTaoRepo;
    private final ModelMapper mapper;
    private final NamDaoTaoRepository namDaoTaoRepo;

    // Read: Lấy danh sách CTDT (bao gồm cả danh sách KeHoachHocTap)
    public List<ChuongTrinhDaoTaoResponse> getAllChuongTrinhDaoTao() {
        return ctdtRepo
                .findAll()
                .stream().map(ctdt -> {
                    ChuongTrinhDaoTaoResponse response = mapper.map(ctdt, ChuongTrinhDaoTaoResponse.class);
                    response.setHeDaoTao(mapper.map(ctdt.getHeDaoTao(), HeDaoTaoResponse.class));
                    response.setBacDaoTao(mapper.map(ctdt.getBacDaoTao(), BacDaoTaoResponse.class));
                    response.setNganhDaoTao(mapper.map(ctdt.getNganhDaoTao(), NganhDaoTaoResponse.class));
                    response.setNamDaoTao(
                            ctdt.getNamDaoTaos().stream()
                                    .map(NamDaoTao::getNam)
                                    .toList());
                    return response;
                })
        .toList();
    }
    // Find: Tìm CTDT theo MaCTDT và trả về thông tin cùng danh sách KeHoachHocTap
    public ChuongTrinhDaoTaoResponse findCTDT(String maCTDT) {
        ChuongTrinhDaoTao ctdt = ctdtRepo.findById(maCTDT)
                .orElseThrow(() -> new AppException("Không tìm thấy chương trình đào tạo"));
        ChuongTrinhDaoTaoResponse response = mapper.map(ctdt, ChuongTrinhDaoTaoResponse.class);
        response.setHeDaoTao(mapper.map(ctdt.getHeDaoTao(), HeDaoTaoResponse.class));
        response.setBacDaoTao(mapper.map(ctdt.getBacDaoTao(), BacDaoTaoResponse.class));
        response.setNganhDaoTao(mapper.map(ctdt.getNganhDaoTao(), NganhDaoTaoResponse.class));
        response.setNamDaoTao(
                ctdt.getNamDaoTaos().stream()
                        .map(NamDaoTao::getNam)
                        .toList());
        return response;
    }

    public String createChuongTrinhDaoTao(ChuongTrinhDaoTaoCreationRequest req) {
        if (ctdtRepo.existsById(req.getMaCTDT())) {
            throw new AppException("Mã chương trình đào tạo đã tồn tại");
        }

        BacDaoTao bacDaoTao = bacDaoTaoRepo.findById(req.getMaBac())
                .orElseThrow(() -> new AppException("Bậc đào tạo không tồn tại"));
        HeDaoTao heDaoTao = heDaoTaoRepo.findById(req.getMaHe())
                .orElseThrow(() -> new AppException("Hệ đào tạo không tồn tại"));
        NganhDaoTao nganhDaoTao = nganhDaoTaoRepo.findById(req.getMaNganh())
                .orElseThrow(() -> new AppException("Ngành đào tạo không tồn tại"));

        ChuongTrinhDaoTao ctdt = mapper.map(req, ChuongTrinhDaoTao.class);
        ctdt.setBacDaoTao(bacDaoTao);
        ctdt.setHeDaoTao(heDaoTao);
        ctdt.setNganhDaoTao(nganhDaoTao);
        ctdt.setNamDaoTaos(
                req.getNamDaoTao().stream()
                .map(nam -> {
                    if (!namDaoTaoRepo.existsById(nam)) throw new AppException("Năm đào tạo không tồn tại");
                    return NamDaoTao.builder().nam(nam).build();
                })
                .toList()
        );

        return ctdtRepo.save(ctdt).getMaCTDT();
    }

    public String updateCTDT(String maCTDT, ChuongTrinhDaoTaoUpdateRequest req) {
        ChuongTrinhDaoTao ctdt = ctdtRepo.findById(maCTDT)
                .orElseThrow(() -> new AppException("Không tìm thấy chương trình đào tạo"));

        BacDaoTao bacDaoTao = bacDaoTaoRepo.findById(req.getMaBac())
                .orElseThrow(() -> new AppException("Không tìm thấy bậc đào tạo"));
        HeDaoTao heDaoTao = heDaoTaoRepo.findById(req.getMaHe())
                .orElseThrow(() -> new AppException("Không tìm thấy hình thức đào tạo"));
        NganhDaoTao nganhDaoTao = nganhDaoTaoRepo.findById(req.getMaNganh())
                .orElseThrow(() -> new AppException("Không tìm thấy ngành đào tạo"));

        mapper.map(req, ctdt);
        ctdt.setBacDaoTao(bacDaoTao);
        ctdt.setHeDaoTao(heDaoTao);
        ctdt.setNganhDaoTao(nganhDaoTao);
        if(! Objects.isNull(req.getNamDaoTao())){

            List<NamDaoTao> namDaoTaos = req.getNamDaoTao().stream()
                    .map(nam -> {
                        if (!namDaoTaoRepo.existsById(nam)) throw new AppException("Năm đào tạo không tồn tại");
                        return NamDaoTao.builder().nam(nam).build();
                    })
                    .toList();

            ctdt.getNamDaoTaos().clear();
            ctdt.getNamDaoTaos().addAll(namDaoTaos);
        }


        return ctdtRepo.save(ctdt).getMaCTDT();
    }

    // Delete: Xóa CTDT theo MaCTDT
    public void deleteCTDT(String maCTDT) {
        if (!ctdtRepo.existsById(maCTDT)) {
            throw new AppException("Không tìm thấy chương trình đào tạo");
        }
        ctdtRepo.deleteById(maCTDT);
    }



}
