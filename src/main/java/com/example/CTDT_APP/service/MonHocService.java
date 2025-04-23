package com.example.CTDT_APP.service;

import com.example.CTDT_APP.constant.TrangThai;
import com.example.CTDT_APP.dto.request.MonHocCreationRequest;
import com.example.CTDT_APP.dto.request.MonHocUpdateRequest;
import com.example.CTDT_APP.dto.response.MonHocResponse;
import com.example.CTDT_APP.dto.response.QuanHeMonHocResponse;
import com.example.CTDT_APP.entity.KhoiKienThuc;
import com.example.CTDT_APP.entity.MonHoc;
import com.example.CTDT_APP.entity.QuanHeMonHoc;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.KhoiKienThucRepository;
import com.example.CTDT_APP.repository.MonHocRepository;
import com.example.CTDT_APP.repository.QuanHeMonHocRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
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
    private final QuanHeMonHocRepository quanHeMonHocRepository;

    public List<MonHocResponse> getAllMonHoc() {
        return monHocRepo.findAll().stream()
                .map(monHoc -> MonHocResponse.builder()
                        .maMon(monHoc.getMaMon())
                        .tenMon(monHoc.getTenMon())
                        .soTinChi(monHoc.getSoTinChi())
                        .soTietLyThuyet(monHoc.getSoTietLyThuyet())
                        .soTietThucHanh(monHoc.getSoTietThucHanh())
                        .soTietTuHoc(monHoc.getSoTietTuHoc())
                        .soTietBaiTap(monHoc.getSoTietBaiTap())
                        .ngonNguGiangDay(monHoc.getNgonNguGiangDay().getValue())
                        .trangThai(monHoc.getTrangThai().getValue())
                        .khoiKienThuc(monHoc.getKhoiKienThuc())
                        .quanHeMonHoc(monHoc.getDsMonLienQuan().stream()
                                .map(quanHeMonHoc -> QuanHeMonHocResponse.builder()
                                        .maMonLienQuan(quanHeMonHoc.getMonLienQuan().getMaMon())
                                        .dieuKien(quanHeMonHoc.getLoaiDieuKien().getValue())
                                        .build())
                                .toList())
                        .build())
                .toList();
    }

    @Transactional
    public String createMonHoc(MonHocCreationRequest req) {
        if (monHocRepo.existsById(req.getMaMon())) throw new AppException("Mã môn học đã tồn tại");

        KhoiKienThuc khoiKienThuc = khoiKienThucRepo.findById(req.getMaKhoi())
                .orElseThrow(() -> new AppException("Khối kiến thức không tồn tại"));

        MonHoc monHoc = MonHoc.builder()
                .maMon(req.getMaMon())
                .tenMon(req.getTenMon())
                .soTinChi(req.getSoTinChi())
                .soTietLyThuyet(req.getSoTietLyThuyet())
                .soTietBaiTap(req.getSoTietBaiTap())
                .soTietThucHanh(req.getSoTietThucHanh())
                .soTietTuHoc(req.getSoTietTuHoc())
                .ngonNguGiangDay(req.getNgonNguGiangDay())
                .trangThai(req.getTrangThai() == null ? TrangThai.HOAT_DONG : req.getTrangThai())
                .khoiKienThuc(khoiKienThuc)
                .build();

        String maMon = monHocRepo.save(monHoc).getMaMon();

        Optional
                .ofNullable(req.getQuanHeMonHoc())
                .ifPresent(quanHeMonHocRequests -> {
                    monHocLienQuanService.createOrUpdateMonHocLienQuan(maMon, quanHeMonHocRequests);
                });

        return maMon;
    }

    @Transactional
    public MonHocResponse updateMonHoc(String maMonHoc, MonHocUpdateRequest req) {
        MonHoc monHoc = monHocRepo.findById(maMonHoc)
                .orElseThrow(() -> new AppException("Môn học không tồn tại"));

        KhoiKienThuc khoiKienThuc = khoiKienThucRepo.findById(req.getMaKhoi())
                .orElseThrow(() -> new AppException("Khối kiến thức không tồn tại"));
        log.info(khoiKienThuc.getTenKhoi());
        mapper.map(req, monHoc);
        monHoc.setKhoiKienThuc(khoiKienThuc);

        return mapper.map(
                monHocRepo.save(monHoc), MonHocResponse.class
        );
    }

    @Transactional
    public void deleteMonHoc(String maMonHoc) {
        MonHoc monHoc = monHocRepo.findById(maMonHoc)
                .orElseThrow(() -> new AppException("Không tìm thấy môn học"));

        if(!monHoc.getKiHocs().isEmpty()){
            throw new AppException("Không thể xóa môn học này vì đang được sử dụng trong chương trình đào tạo");
        }


        try {
            monHocRepo.deleteById(maMonHoc);
        } catch (DataIntegrityViolationException e) {
            if(e.getRootCause() instanceof SQLIntegrityConstraintViolationException){
                throw new AppException("Không thể xóa môn học này vì đang là môn tiên quyết/học trước của môn học khác");
            }
        }
    }
}
