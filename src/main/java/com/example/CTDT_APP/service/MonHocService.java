package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.MonHocCreationRequest;
import com.example.CTDT_APP.dto.request.MonHocUpdateRequest;
import com.example.CTDT_APP.dto.response.MonHocResponse;
import com.example.CTDT_APP.entity.KhoiKienThuc;
import com.example.CTDT_APP.entity.MonHoc;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.KhoiKienThucRepository;
import com.example.CTDT_APP.repository.MonHocRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class MonHocService {
    private final MonHocRepository monHocRepo;
    private final KhoiKienThucRepository khoiKienThucRepo;
    private final ModelMapper mapper;
    private final MonHocLienQuanService monHocLienQuanService;

    public List<MonHoc> getAllMonHoc() {
        return monHocRepo.findAll();
    }

    @Transactional
    public MonHocResponse createMonHoc(MonHocCreationRequest req) {
        if (monHocRepo.existsById(req.getMaMon())) throw new AppException("Mã môn học đã tồn tại");
        MonHoc monHoc = mapper.map(req, MonHoc.class);

        KhoiKienThuc khoiKienThuc = khoiKienThucRepo.findById(req.getMaKhoi())
                .orElseThrow(() -> new AppException("Khối kiến thức không tồn tại"));

        Optional
                .ofNullable(req.getQuanHeMonHocs())
                .ifPresent(quanHeMonHocRequests -> {
                    monHocLienQuanService.createMonHocLienQuan(req.getMaMon(), quanHeMonHocRequests);
                });
        monHoc.setKhoiKienThuc(khoiKienThuc);

        return mapper.map(
                monHocRepo.save(monHoc), MonHocResponse.class
        );
    }

    public MonHocResponse updateMonHoc(String maMonHoc, MonHocUpdateRequest req) {
        MonHoc monHoc = monHocRepo.findById(maMonHoc)
                .orElseThrow(() -> new AppException("Môn học không tồn tại"));

        KhoiKienThuc khoiKienThuc = khoiKienThucRepo.findById(req.getMaKhoi())
                .orElseThrow(() -> new AppException("Khối kiến thức không tồn tại"));

        mapper.map(req, monHoc);
        monHoc.setKhoiKienThuc(khoiKienThuc);

        return mapper.map(
                monHocRepo.save(monHoc), MonHocResponse.class
        );
    }

    public void deleteMonHoc(String maMonHoc) {
        if (!monHocRepo.existsById(maMonHoc)) throw new AppException("Môn học không tồn tại");
        monHocRepo.deleteById(maMonHoc);
    }
}
