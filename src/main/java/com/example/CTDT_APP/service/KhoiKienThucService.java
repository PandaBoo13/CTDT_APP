package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.KhoiKienThucCreationRequest;
import com.example.CTDT_APP.dto.response.KhoiKienThucResponse;
import com.example.CTDT_APP.entity.KhoiKienThuc;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.KhoiKienThucRepository;
import com.example.CTDT_APP.repository.MonHocRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class KhoiKienThucService {

    private final KhoiKienThucRepository khoiKienThucRepo;
    private final ModelMapper mapper;

    public List<KhoiKienThucResponse> getAllKhoiKienThuc() {
        return khoiKienThucRepo.findAllByParentIsNull()
                .stream()
                .map(this::toTreeDto)
                .toList();
    }

    @Transactional
    public KhoiKienThuc createKhoiKienThuc(KhoiKienThucCreationRequest req) {
        if (khoiKienThucRepo.existsById(req.getMaKhoi())) throw new AppException("Mã khối kiến thức đã tồn tại");

        KhoiKienThuc khoi = mapper.map(req, KhoiKienThuc.class);

        Optional.ofNullable(req.getParent())
                .map(parentId -> khoiKienThucRepo.findById(parentId)
                        .orElseThrow(() -> new AppException("Khối kiến thức cha không tồn tại")))
                .ifPresent(parent -> {
                    validateNoCycle(khoi, parent);
                    khoi.setParent(parent);
                });

        return khoiKienThucRepo.save(khoi);
    }

    public KhoiKienThuc updateKhoiKienThuc(String maKhoi, KhoiKienThucCreationRequest req) {
        KhoiKienThuc khoi = khoiKienThucRepo.findById(maKhoi)
                .orElseThrow(() -> new AppException("Khối kiến thức không tồn tại"));

        mapper.map(req, khoi);

        Optional.ofNullable(req.getParent())
                .map(parentId -> khoiKienThucRepo.findById(parentId)
                        .orElseThrow(() -> new AppException("Khối kiến thức cha không tồn tại")))
                .ifPresent(
                        parent -> {
                            validateNoCycle(khoi, parent);
                            khoi.setParent(parent);
                        }
                );

        return khoiKienThucRepo.save(khoi);
    }


    public void deleteKhoiKienThuc(String maKhoi) {
        khoiKienThucRepo
                .findById(maKhoi)
                .map(khoi -> {
                    if (!khoi.getChildren().isEmpty())
                        throw new AppException("Khối kiến thức này có khối kiến thức con, không thể xóa");
                    return khoi;
                })
//                .ifPresentOrElse(
//                        khoi -> khoiKienThucRepo.deleteById(maKhoi),
//                        () -> {
//                            throw new AppException("Khối kiến thức không tồn tại");
//                        }
//                )
                .ifPresent(khoiKienThuc -> {
                    try{
                        khoiKienThucRepo.deleteById(maKhoi);
                    } catch(DataIntegrityViolationException e){
                        if(e.getRootCause() instanceof SQLIntegrityConstraintViolationException){
                            throw new AppException("Không thể xóa vì đã có môn học tham chiếu đến khối kiến thức này");
                        }
                        throw new AppException("Khối kiến thức không tồn tại");
                    }
                });
                ;
    }

    private KhoiKienThucResponse toTreeDto(KhoiKienThuc khoiKienThuc) {
        KhoiKienThucResponse dto = mapper.map(khoiKienThuc, KhoiKienThucResponse.class);

        List<KhoiKienThucResponse> childrenDto = khoiKienThuc.getChildren().stream()
                .map(this::toTreeDto)
                .toList();

        dto.setChildren(childrenDto);
        return dto;
    }

    private void validateNoCycle(KhoiKienThuc current, KhoiKienThuc newParent) {
        KhoiKienThuc temp = newParent;
        while (temp != null) {
            if (temp.getMaKhoi().equals(current.getMaKhoi())) {
                throw new AppException("Không thể tạo vòng lặp phân cấp)");
            }
            temp = temp.getParent();
        }
    }
}
