package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.KeHoachHocTapCreationRequest;
import com.example.CTDT_APP.dto.request.KeHoachHocTapUpdateRequest;
import com.example.CTDT_APP.dto.response.ChuyenNganhBriefResponse;
import com.example.CTDT_APP.dto.response.KeHoachHocTapResponse;
import com.example.CTDT_APP.entity.*;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.ChuongTrinhDaoTaoRepository;
import com.example.CTDT_APP.repository.ChuyenNganhRepository;
import com.example.CTDT_APP.repository.KeHoachHocTapRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class KeHoachHocTapService {

    private final KeHoachHocTapRepository keHoachHocTapRepo;
    private final ChuongTrinhDaoTaoRepository chuongTrinhDaoTaoRepo;
    private final ChuyenNganhRepository chuyenNganhRepo;
    private final ModelMapper modelMapper;

    public List<KeHoachHocTapResponse> getAllKeHoachHocTap(String maCTDT) {
        ChuongTrinhDaoTao chuongTrinhDaoTao = chuongTrinhDaoTaoRepo.findById(maCTDT)
                .orElseThrow(() -> new AppException("Chương trình đào tạo không tồn tại"));

        return keHoachHocTapRepo.findAllByChuongTrinhDaoTao(chuongTrinhDaoTao).stream()
                .map(keHoachHocTap -> {

                    ChuyenNganhBriefResponse chuyenNganh = modelMapper.map(
                            keHoachHocTap.getChuyenNganh(),
                            ChuyenNganhBriefResponse.class
                    );

                    return KeHoachHocTapResponse.builder()
                            .maKHHT(keHoachHocTap.getMaKHHT())
                            .chuyenNganh(chuyenNganh)
                            .moTa(keHoachHocTap.getMoTa())
                            .build();
                })
                .toList();
    }

    public String createKeHoachHocTap(String maCTDT, KeHoachHocTapCreationRequest req) {
        ChuongTrinhDaoTao chuongTrinhDaoTao = chuongTrinhDaoTaoRepo.findById(maCTDT)
                .orElseThrow(() -> new AppException("Chương trình đào tạo không tồn tại"));

        ChuyenNganh chuyenNganh = chuyenNganhRepo.findById(req.getMaChuyenNganh())
                .orElseThrow(() -> new AppException("Chuyên ngành không tồn tại"));

        KeHoachHocTap keHoachHocTap = KeHoachHocTap.builder()
                .chuongTrinhDaoTao(chuongTrinhDaoTao)
                .moTa(req.getMoTa())
                .chuyenNganh(chuyenNganh)
                .build();

        return keHoachHocTapRepo.save(keHoachHocTap).getMaKHHT();
    }

    public String updateKeHoachHocTap(String maKHHT, KeHoachHocTapUpdateRequest req) {
        KeHoachHocTap keHoachHocTap = keHoachHocTapRepo.findById(maKHHT)
                .orElseThrow(() -> new AppException("Kế hoạch học tập không tồn tại"));

        ChuyenNganh chuyenNganh = chuyenNganhRepo.findById(req.getMaChuyenNganh())
                .orElseThrow(() -> new AppException("Chuyên ngành không tồn tại"));

        keHoachHocTap.setMoTa(req.getMoTa());
        keHoachHocTap.setChuyenNganh(chuyenNganh);

        return keHoachHocTapRepo.save(keHoachHocTap).getMaKHHT();
    }

    @Transactional
    public void deleteKeHoachHocTap(String maKHHT) {
        KeHoachHocTap keHoachHocTap = keHoachHocTapRepo.findById(maKHHT)
                .orElseThrow(() -> new AppException("Kế hoạch học tập không tồn tại"));

        for (KiHoc kiHoc : keHoachHocTap.getKiHocs()) {
            for (MonHoc monHoc : kiHoc.getMonHocs()) {
                monHoc.getKiHocs().remove(kiHoc);
            }
            kiHoc.getMonHocs().clear();
        }

        keHoachHocTapRepo.delete(keHoachHocTap);
    }

//    // Read: Lấy tất cả các KeHoachHocTap theo mã CTDT, chuyển đổi sang DTO response có thêm trường tenChuyenNganh
//    public List<KeHoachHocTapResponse> getAllKehoachHocTap(String maCTDT) {
//        ChuongTrinhDaoTao chuongTrinhDaoTao = chuongTrinhDaoTaoRepo.findById(maCTDT)
//                .orElseThrow(() -> new AppException("Chương trình đào tạo không tồn tại"));
//
//        return keHoachHocTapRepo
//                .findAllByChuongTrinhDaoTao(chuongTrinhDaoTao).stream()
//                .map(keHoachHocTap ->
//                        KeHoachHocTapResponse.builder()
//                                .maKHHT(keHoachHocTap.getMaKHHT())
//                                .maCTDT(keHoachHocTap.getChuongTrinhDaoTao().getMaCTDT())
//                                // Bổ sung trường tenChuyenNganh từ entity ChuyenNganh
//                                .maChuyenNganh(keHoachHocTap.getChuyenNganh().getMaChuyenNganh())
//                                .tenChuyenNganh(keHoachHocTap.getChuyenNganh().getTenChuyenNganh())
//                                .moTa(keHoachHocTap.getMoTa())
//                                .build())
//                .collect(Collectors.toList());
//    }
//
//    // Read: Lấy chi tiết KeHoachHocTap theo mã KHHT, trả về thông tin chi tiết các KiHoc và danh sách MonHoc của mỗi KiHoc
//    public List<KeHoachHocTapDetailsResponse> getAllKeHoachHocTapDetails(String maKTHHT) {
//        KeHoachHocTap keHoachHocTap = keHoachHocTapRepo.findById(maKTHHT)
//                .orElseThrow(() -> new AppException("Kế hoạch học tập không tồn tại"));
//
//        return keHoachHocTap.getKiHocs().stream()
//                .map(kiHoc -> KeHoachHocTapDetailsResponse.builder()
//                        .ki(kiHoc.getKi())
//                        .monHocs(kiHoc.getMonHocs().stream()
//                                .map(monHoc -> modelMapper.map(monHoc, MonHocResponse.class))
//                                .collect(Collectors.toSet()))
//                        .build())
//                .collect(Collectors.toList());
//    }
}
