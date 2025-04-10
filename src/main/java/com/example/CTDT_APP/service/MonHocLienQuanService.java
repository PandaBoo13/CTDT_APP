package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.QuanHeMonHocRequest;
import com.example.CTDT_APP.entity.MonHoc;
import com.example.CTDT_APP.entity.QuanHeMonHoc;
import com.example.CTDT_APP.entity.QuanHeMonHocId;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.MHLQRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MonHocLienQuanService {
    private final MHLQRepository mhlqRepo;

    public void createMonHocLienQuan(String maMon, List<QuanHeMonHocRequest> req) {
        List<QuanHeMonHoc> quanHeMonHocs = req.stream().map(item -> {
            if (!mhlqRepo.existsById(item.getMaMon())) throw new AppException("Môn học không tồn tại");
            return QuanHeMonHoc.builder()
                    .id(new QuanHeMonHocId())
                    .monHocChinh(MonHoc.builder().maMon(maMon).build())
                    .monHocLienQuan(MonHoc.builder().maMon(item.getMaMon()).build())
                    .loaiDieuKien(item.getDieuKien())
                    .build();
        }).toList();
        mhlqRepo.saveAll(quanHeMonHocs);
    }
}
