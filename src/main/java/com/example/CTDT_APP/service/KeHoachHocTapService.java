package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.KeHoachHocTapCreationRequest;
import com.example.CTDT_APP.dto.response.KeHoachHocTapDetailsResponse;
import com.example.CTDT_APP.dto.response.KeHoachHocTapResponse;
import com.example.CTDT_APP.dto.response.MonHocResponse;
import com.example.CTDT_APP.entity.ChuongTrinhDaoTao;
import com.example.CTDT_APP.entity.ChuyenNganh;
import com.example.CTDT_APP.entity.KeHoachHocTap;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.ChuongTrinhDaoTaoRepository;
import com.example.CTDT_APP.repository.ChuyenNganhRepository;
import com.example.CTDT_APP.repository.KeHoachHocTapRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class KeHoachHocTapService {

    private final KeHoachHocTapRepository keHoachHocTapRepo;
    private final ChuongTrinhDaoTaoRepository chuongTrinhDaoTaoRepo;
    private final ChuyenNganhRepository chuyenNganhRepo;
    private final ModelMapper modelMapper;

    public KeHoachHocTap createKeHoachHocTap(KeHoachHocTapCreationRequest req) {
        ChuongTrinhDaoTao chuongTrinhDaoTao = chuongTrinhDaoTaoRepo.findById(req.getMaCTDT())
                .orElseThrow(() -> new AppException("Chương trình đào tạo không tồn tại"));

        ChuyenNganh chuyenNganh = chuyenNganhRepo.findById(req.getMaChuyenNganh())
                .orElseThrow(() -> new AppException("Chuyên ngành không tồn tại"));

        KeHoachHocTap keHoachHocTap = KeHoachHocTap.builder()
                .chuongTrinhDaoTao(chuongTrinhDaoTao)
                .chuyenNganh(chuyenNganh)
                .build();

        return keHoachHocTapRepo.save(keHoachHocTap);
    }

    // Read: Lấy tất cả các KeHoachHocTap theo mã CTDT, chuyển đổi sang DTO response có thêm trường tenChuyenNganh
    public List<KeHoachHocTapResponse> getAllKehoachHocTap(String maCTDT) {
        ChuongTrinhDaoTao chuongTrinhDaoTao = chuongTrinhDaoTaoRepo.findById(maCTDT)
                .orElseThrow(() -> new AppException("Chương trình đào tạo không tồn tại"));

        return keHoachHocTapRepo
                .findAllByChuongTrinhDaoTao(chuongTrinhDaoTao).stream()
                .map(keHoachHocTap ->
                        KeHoachHocTapResponse.builder()
                                .maKHHT(keHoachHocTap.getMaKHHT())
                                .maCTDT(keHoachHocTap.getChuongTrinhDaoTao().getMaCTDT())
                                // Bổ sung trường tenChuyenNganh từ entity ChuyenNganh
                                .maChuyenNganh(keHoachHocTap.getChuyenNganh().getMaChuyenNganh())
                                .tenChuyenNganh(keHoachHocTap.getChuyenNganh().getTenChuyenNganh())
                                .moTa(keHoachHocTap.getMoTa())
                                .build())
                .collect(Collectors.toList());
    }

    // Read: Lấy chi tiết KeHoachHocTap theo mã KHHT, trả về thông tin chi tiết các KiHoc và danh sách MonHoc của mỗi KiHoc
    public List<KeHoachHocTapDetailsResponse> getAllKeHoachHocTapDetails(String maKTHHT) {
        KeHoachHocTap keHoachHocTap = keHoachHocTapRepo.findById(maKTHHT)
                .orElseThrow(() -> new AppException("Kế hoạch học tập không tồn tại"));

        return keHoachHocTap.getKiHocs().stream()
                .map(kiHoc -> KeHoachHocTapDetailsResponse.builder()
                        .ki(kiHoc.getKi())
                        .monHocs(kiHoc.getMonHocs().stream()
                                .map(monHoc -> modelMapper.map(monHoc, MonHocResponse.class))
                                .collect(Collectors.toSet()))
                        .build())
                .collect(Collectors.toList());
    }
}
