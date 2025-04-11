package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.KTTCreationRequest;
import com.example.CTDT_APP.entity.KhoiKienThuc;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.KhoiKienThucRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class KhoiKienThucService {

    private final KhoiKienThucRepository khoiKienThucRepo;
    private final ModelMapper mapper;

    public List<KhoiKienThuc> getAllKhoiKienThuc() {
        return khoiKienThucRepo.findAll();
    }

    public KhoiKienThuc createKhoiKienThuc(KTTCreationRequest req) {

        if (khoiKienThucRepo.existsById(req.getMaKhoi())) throw new AppException("Mã khối kiến thức đã tồn tại");

        KhoiKienThuc khoiKienThuc = mapper.map(req, KhoiKienThuc.class);

        return khoiKienThucRepo.save(khoiKienThuc);
    }

    public KhoiKienThuc updateKhoiKienThuc(String maKhoi, KTTCreationRequest req) {
        KhoiKienThuc khoiKienThuc = khoiKienThucRepo.findById(maKhoi)
                .orElseThrow(() -> new AppException("Khối kiến thức không tồn tại"));

        mapper.map(req, khoiKienThuc);

        return khoiKienThucRepo.save(khoiKienThuc);
    }

    public void deleteKhoiKienThuc(String maKhoi) {
        if (!khoiKienThucRepo.existsById(maKhoi)) throw new AppException("Khối kiến thức không tồn tại");
        khoiKienThucRepo.deleteById(maKhoi);
    }
}
