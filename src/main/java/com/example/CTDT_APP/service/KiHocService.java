package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.KiHocCreationRequest;
import com.example.CTDT_APP.dto.request.KiHocMonHocCreationRequest;
import com.example.CTDT_APP.dto.request.KiHocUpdateRequest;
import com.example.CTDT_APP.dto.response.MonHocBriefResponse;
import com.example.CTDT_APP.entity.KeHoachHocTap;
import com.example.CTDT_APP.entity.KiHoc;
import com.example.CTDT_APP.entity.KihocMonhocId;
import com.example.CTDT_APP.entity.MonHoc;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.KeHoachHocTapRepository;
import com.example.CTDT_APP.repository.KiHocMonHocRepository;
import com.example.CTDT_APP.repository.KiHocRepository;
import com.example.CTDT_APP.repository.MonHocRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class KiHocService {
    private final KiHocRepository kiHocRepo;
    private final KeHoachHocTapRepository keHoachHocTapRepo;
    private final MonHocRepository monHocRepo;
    private final KiHocMonHocRepository kiHocMonHocRepo;
    private final ModelMapper mapper;
    private final EntityManager entityManager;

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

    @Transactional
    public String createMonHocToKiHoc(String maKi, KiHocMonHocCreationRequest req) {
        if (!kiHocRepo.existsById(maKi)) throw new AppException("Kỳ học không tồn tại");
        if (!monHocRepo.existsById(req.getMaMon())) throw new AppException("Môn học không tồn tại");

        entityManager.createNativeQuery("CALL sp_them_mon_vao_kihoc(?1, ?2, ?3)")
                .setParameter(1, maKi)
                .setParameter(2, req.getMaMon())
                .setParameter(3, req.getLoaiMonHoc())
                .executeUpdate();

//        if (kiHocMonHocRepo.existsById(new KihocMonhocId(maKi, req.getMaMon()))) {
//            throw new AppException("Môn học đã được thêm vào kỳ học này");
//        }
//
//        KiHocMonHoc kihocMonhoc = KiHocMonHoc.builder()
//                .id(new KihocMonhocId(maKi, req.getMaMon()))
//                .monHoc(monHoc)
//                .kiHoc(kiHoc)
//                .loaiMonHoc(req.getLoaiMonHoc())
//                .build();
//
//        kiHocMonHocRepo.save(kihocMonhoc);

        return maKi;
    }

    @Transactional
    public void deleteKiHoc(String maKi) {
        KiHoc kiHoc = kiHocRepo.findById(maKi)
                .orElseThrow(() -> new AppException("Kỳ học không tồn tại"));

        for (MonHoc monHoc : kiHoc.getMonHocs()) {
            monHoc.getKiHocs().remove(kiHoc);
        }
        kiHoc.getMonHocs().clear();

        kiHocRepo.delete(kiHoc);
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
