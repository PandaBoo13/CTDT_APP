package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.KiHocCreationRequest;
import com.example.CTDT_APP.dto.request.KiHocMonHocCreationRequest;
import com.example.CTDT_APP.dto.request.KiHocUpdateRequest;
import com.example.CTDT_APP.dto.response.MonHocBriefResponse;
import com.example.CTDT_APP.entity.*;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.KeHoachHocTapRepository;
import com.example.CTDT_APP.repository.KiHocMonHocRepository;
import com.example.CTDT_APP.repository.KiHocRepository;
import com.example.CTDT_APP.repository.MonHocRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class KiHocService {
    private final KiHocRepository kiHocRepo;
    private final KeHoachHocTapRepository keHoachHocTapRepo;
    private final MonHocRepository monHocRepo;
    private final KiHocMonHocRepository kiHocMonHocRepo;
    private final ModelMapper mapper;

    public List<KiHoc> getAllKiHoc(String maKHHT) {
        KeHoachHocTap keHoachHocTap = keHoachHocTapRepo.findById(maKHHT)
                .orElseThrow(() -> new AppException("Kế hoạch học tập không tồn tại"));
        return kiHocRepo.findAllByKeHoachHocTap(keHoachHocTap);
    }

    public String createKiHoc(String maKHHT, KiHocCreationRequest req) {
        KeHoachHocTap keHoachHocTap = keHoachHocTapRepo.findById(maKHHT)
                .orElseThrow(() -> new AppException(("Kế hoạch học tập không tồn tại")));

        if (kiHocRepo.existsByKiAndKeHoachHocTap(req.getKi(), keHoachHocTap)) {
            throw new AppException("Kỳ học đã tồn tại");
        }

        KiHoc kiHoc = KiHoc.builder()
                .ki(req.getKi())
                .keHoachHocTap(keHoachHocTap)
                .build();

        return kiHocRepo.save(kiHoc).getMaKi();
    }

    public String updateKiHoc(String maKi, KiHocUpdateRequest req) {
        KiHoc kiHoc = kiHocRepo.findById(maKi)
                .orElseThrow(() -> new AppException("Kỳ học không tồn tại"));

        if (kiHocRepo.existsByKiAndKeHoachHocTap(req.getKi(), kiHoc.getKeHoachHocTap())) {
            throw new AppException("Kỳ học đã tồn tại");
        }

        kiHoc.setKi(req.getKi());

        return kiHocRepo.save(kiHoc).getMaKi();
    }

    public String createMonHocToKiHoc(String maKi, KiHocMonHocCreationRequest req) {
        KiHoc kiHoc = kiHocRepo.findById(maKi)
                .orElseThrow(() -> new AppException("Kỳ học không tồn tại"));

        MonHoc monHoc = monHocRepo.findById(req.getMaMon())
                .orElseThrow(() -> new AppException("Môn học không tồn tại"));

        if (kiHocMonHocRepo.existsById(new KihocMonhocId(maKi, req.getMaMon()))) {
            throw new AppException("Môn học đã được thêm vào kỳ học này");
        }

        KiHocMonHoc kihocMonhoc = KiHocMonHoc.builder()
                .id(new KihocMonhocId(maKi, req.getMaMon()))
                .monHoc(monHoc)
                .kiHoc(kiHoc)
                .loaiMonHoc(req.getLoaiMonHoc())
                .build();

        kiHocMonHocRepo.save(kihocMonhoc);

        return monHoc.getMaMon();
    }

    public List<MonHocBriefResponse> getMonHocByKiHoc(String maKi) {
        KiHoc kiHoc = kiHocRepo.findById(maKi)
                .orElseThrow(() -> new AppException("Kỳ học không tồn tại"));

        return kiHoc.getMonHocs()
                .stream()
                .map(monHoc -> mapper.map(monHoc, MonHocBriefResponse.class))
                .toList();
    }

    public void deleteMonHocFromKiHoc(String maKi, String maMon) {
        if (!kiHocRepo.existsById(maKi)) throw new AppException("Kỳ học không tồn tại");
        if (!monHocRepo.existsById(maMon)) throw new AppException("Môn học không tồn tại");

        kiHocMonHocRepo.deleteById(new KihocMonhocId(maKi, maMon));
    }
}
