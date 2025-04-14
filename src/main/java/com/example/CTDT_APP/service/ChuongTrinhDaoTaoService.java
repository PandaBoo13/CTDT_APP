package com.example.CTDT_APP.service;

import com.example.CTDT_APP.constant.TrangThai;
import com.example.CTDT_APP.dto.request.ChuongTrinhDaoTaoCreationRequest;
import com.example.CTDT_APP.dto.request.ChuongTrinhDaoTaoUpdateRequest;
import com.example.CTDT_APP.dto.response.ChuongTrinhDaoTaoResponse;
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

    // Read: Lấy danh sách CTDT (bao gồm cả danh sách KeHoachHocTap)
    public List<ChuongTrinhDaoTaoResponse> getAllCTDT() {
        return ctdtRepo.findAll()
                .stream()
                .map(ctdt -> mapper.map(ctdt, ChuongTrinhDaoTaoResponse.class))
                .collect(Collectors.toList());
    }

    // Create: Tạo mới CTDT
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
        // Thiết lập trạng thái (ép kiểu từ String sang enum)
        ctdt.setTrangThai(TrangThai.valueOf(req.getTrangThai().toUpperCase().replace(" ", "_")));
        ctdt.setBacDaoTao(bacDaoTao);
        ctdt.setHeDaoTao(heDaoTao);
        ctdt.setNganhDaoTao(nganhDaoTao);

        ChuongTrinhDaoTao saved = ctdtRepo.save(ctdt);
        return mapper.map(saved, ChuongTrinhDaoTaoResponse.class);
    }

    // Update: Cập nhật CTDT (ngoại trừ MaCTDT)
    public ChuongTrinhDaoTaoResponse updateCTDT(String maCTDT, ChuongTrinhDaoTaoUpdateRequest req) {
        ChuongTrinhDaoTao ctdt = ctdtRepo.findById(maCTDT)
                .orElseThrow(() -> new AppException("Không tìm thấy chương trình đào tạo"));

        BacDaoTao bacDaoTao = bacDaoTaoRepo.findById(req.getMaBac())
                .orElseThrow(() -> new AppException("Không tìm thấy bậc đào tạo"));
        HeDaoTao heDaoTao = heDaoTaoRepo.findById(req.getMaHe())
                .orElseThrow(() -> new AppException("Không tìm thấy hình thức đào tạo"));
        NganhDaoTao nganhDaoTao = nganhDaoTaoRepo.findById(req.getMaNganh())
                .orElseThrow(() -> new AppException("Không tìm thấy ngành đào tạo"));

        // Map các thuộc tính cập nhật
        mapper.map(req, ctdt);
        ctdt.setTrangThai(TrangThai.valueOf(req.getTrangThai().toUpperCase().replace(" ", "_")));
        ctdt.setBacDaoTao(bacDaoTao);
        ctdt.setHeDaoTao(heDaoTao);
        ctdt.setNganhDaoTao(nganhDaoTao);

        ChuongTrinhDaoTao updated = ctdtRepo.save(ctdt);
        return mapper.map(updated, ChuongTrinhDaoTaoResponse.class);
    }

    // Delete: Xóa CTDT theo MaCTDT
    public void deleteCTDT(String maCTDT) {
        if (!ctdtRepo.existsById(maCTDT)) {
            throw new AppException("Không tìm thấy chương trình đào tạo");
        }
        ctdtRepo.deleteById(maCTDT);
    }

    // Find: Tìm CTDT theo MaCTDT và trả về thông tin cùng danh sách KeHoachHocTap
    public ChuongTrinhDaoTaoResponse findCTDT(String maCTDT) {
        ChuongTrinhDaoTao ctdt = ctdtRepo.findById(maCTDT)
                .orElseThrow(() -> new AppException("Không tìm thấy chương trình đào tạo"));
        return mapper.map(ctdt, ChuongTrinhDaoTaoResponse.class);
    }
}
