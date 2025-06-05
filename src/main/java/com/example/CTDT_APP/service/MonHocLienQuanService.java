package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.QuanHeMonHocRequest;
import com.example.CTDT_APP.entity.MonHoc;
import com.example.CTDT_APP.entity.QuanHeMonHoc;
import com.example.CTDT_APP.entity.QuanHeMonHocId;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.MonHocRepository;
import com.example.CTDT_APP.repository.QuanHeMonHocRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MonHocLienQuanService {
    private final QuanHeMonHocRepository quanHeMonHocRepo;
    private final MonHocRepository monHocRepo;
    private final EntityManager entityManager;

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

    @Transactional
    public void updateMonHocLienQuan(String maMonChinh, List<QuanHeMonHocRequest> req) {
        MonHoc monChinh = monHocRepo.findById(maMonChinh).orElseThrow(() -> new AppException("Môn học chính không tồn tại"));
        List<QuanHeMonHoc> existingQuanHeMonHocs = monChinh.getDsMonLienQuan(); // Giả sử getDsMonLienQuan trả về List

        List<QuanHeMonHocId> newQuanHeMonHocIds = req.stream()
                .map(item -> new QuanHeMonHocId(maMonChinh, item.getMaMonLienQuan()))
                .toList();

        List<QuanHeMonHoc> toBeDeleted = existingQuanHeMonHocs.stream()
                .filter(qh -> !newQuanHeMonHocIds.contains(qh.getId()))
                .toList();

        toBeDeleted.forEach(qh -> {
            entityManager.remove(qh);
        });
        existingQuanHeMonHocs.removeAll(toBeDeleted);

        for (QuanHeMonHocRequest item : req) {
            String maMonLienQuan = item.getMaMonLienQuan();
            if (!monHocRepo.existsById(maMonLienQuan)) {
                throw new AppException("Môn học liên quan không tồn tại: " + maMonLienQuan);
            }
            QuanHeMonHocId quanHeMonHocId = new QuanHeMonHocId(maMonChinh, maMonLienQuan);
            Optional<QuanHeMonHoc> existingQuanHe = existingQuanHeMonHocs.stream()
                    .filter(qh -> qh.getId().equals(quanHeMonHocId))
                    .findFirst();

            if (existingQuanHe.isPresent()) {
                QuanHeMonHoc qh = existingQuanHe.get();
                qh.setLoaiDieuKien(item.getDieuKien());
            } else {
                MonHoc monLienQuan = monHocRepo.findById(maMonLienQuan)
                        .orElseThrow(() -> new AppException("Không tìm thấy môn học liên quan"));
                QuanHeMonHoc newQuanHe = QuanHeMonHoc.builder()
                        .id(quanHeMonHocId)
                        .monChinh(monChinh)
                        .monLienQuan(monLienQuan)
                        .loaiDieuKien(item.getDieuKien())
                        .build();
                existingQuanHeMonHocs.add(newQuanHe);
                entityManager.persist(newQuanHe);
            }
        }
    }
}
