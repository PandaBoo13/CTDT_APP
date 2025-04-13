package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.QuanHeMonHocRequest;
import com.example.CTDT_APP.entity.MonHoc;
import com.example.CTDT_APP.entity.QuanHeMonHoc;
import com.example.CTDT_APP.entity.QuanHeMonHocId;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.MonHocRepository;
import com.example.CTDT_APP.repository.QuanHeMonHocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MonHocLienQuanService {
    private final QuanHeMonHocRepository quanHeMonHocRepo;
    private final MonHocRepository monHocRepo;

    public void createOrUpdateMonHocLienQuan(String maMonChinh, List<QuanHeMonHocRequest> req) {
        List<QuanHeMonHoc> quanHeMonHocs = req.stream().map(item -> {
            if (!monHocRepo.existsById(item.getMaMonLienQuan())) throw new AppException("Môn học không tồn tại");
            return QuanHeMonHoc.builder()
                    .id(new QuanHeMonHocId(maMonChinh, item.getMaMonLienQuan()))
                    .monChinh(MonHoc.builder().maMon(maMonChinh).build())
                    .monLienQuan(MonHoc.builder().maMon(item.getMaMonLienQuan()).build())
                    .loaiDieuKien(item.getDieuKien())
                    .build();
        }).toList();
        quanHeMonHocRepo.saveAll(quanHeMonHocs);
    }

    public void deleteQuanHeMonHoc(String maMonChinh, String maMonLienQuan) {
        QuanHeMonHocId id = new QuanHeMonHocId(maMonChinh, maMonLienQuan);
        if (!quanHeMonHocRepo.existsById(id)) {
            throw new AppException("Quan hệ không tồn tại");
        }
        quanHeMonHocRepo.deleteById(id);
    }
}
