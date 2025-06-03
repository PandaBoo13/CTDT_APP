package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.KiHocCreationRequest;
import com.example.CTDT_APP.dto.request.KiHocUpdateRequest;
import com.example.CTDT_APP.entity.KeHoachHocTap;
import com.example.CTDT_APP.entity.KiHoc;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.KeHoachHocTapRepository;
import com.example.CTDT_APP.repository.KiHocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class KiHocService {
    private final KiHocRepository kiHocRepo;
    private final KeHoachHocTapRepository keHoachHocTapRepo;

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
}
