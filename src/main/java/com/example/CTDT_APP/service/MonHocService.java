package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.MonHocCreationRequest;
import com.example.CTDT_APP.entity.KhoiKienThuc;
import com.example.CTDT_APP.entity.MonHoc;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.KhoiKienThucRepository;
import com.example.CTDT_APP.repository.MonHocRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MonHocService {
    private final MonHocRepository monHocRepo;
    private final KhoiKienThucRepository khoiKienThucRepo;
    private final ModelMapper mapper;

    public MonHoc createMonHoc(MonHocCreationRequest req) {
        if (monHocRepo.existsById(req.getMaMon())) throw new AppException("Mon hoc da ton tai");
        KhoiKienThuc khoiKienThuc = khoiKienThucRepo.findById(req.getMaKhoi())
                .orElseThrow(() -> new AppException("Khoi kien thuc khong ton tai"));

        MonHoc monHoc = mapper.map(req, MonHoc.class);
        monHoc.setKhoiKienThuc(khoiKienThuc);

        return monHocRepo.save(monHoc);
    }

    public List<MonHoc> getAllMonHoc() {
        return monHocRepo.findAll();
    }

    public MonHoc updateMonHoc(String maMonHoc, MonHocCreationRequest req) {
        MonHoc monHoc = monHocRepo.findById(maMonHoc)
                .orElseThrow(() -> new AppException("Mon hoc khong ton tai"));
        KhoiKienThuc khoiKienThuc = khoiKienThucRepo.findById(req.getMaKhoi())
                .orElseThrow(() -> new AppException("Khoi kien thuc khong ton tai"));

        mapper.map(req, monHoc);
        monHoc.setKhoiKienThuc(khoiKienThuc);

        return monHocRepo.save(monHoc);
    }

    public void deleteMonHoc(String maMonHoc) {
        if (!monHocRepo.existsById(maMonHoc)) throw new AppException("Mon hoc khong ton tai");
        monHocRepo.deleteById(maMonHoc);
    }
}
