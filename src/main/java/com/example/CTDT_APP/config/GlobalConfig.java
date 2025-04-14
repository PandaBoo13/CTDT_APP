package com.example.CTDT_APP.config;

import com.example.CTDT_APP.dto.response.ChuyenNganhReponse;
import com.example.CTDT_APP.dto.response.KeHoachHocTapResponse;
import com.example.CTDT_APP.entity.ChuyenNganh;
import com.example.CTDT_APP.entity.KeHoachHocTap;
import com.example.CTDT_APP.entity.NhanVien;
import com.example.CTDT_APP.entity.NhanVienResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper
                .getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true);

        modelMapper.typeMap(ChuyenNganh.class, ChuyenNganhReponse.class)
                .addMappings(mapper -> mapper.map(ChuyenNganh::getNganhDaoTao, ChuyenNganhReponse::setNganhDaoTao));

        modelMapper.typeMap(NhanVien.class, NhanVienResponse.class)
                .addMappings(mapper -> mapper.map(NhanVien::getTaiKhoan, NhanVienResponse::setTrangThai));

        // Custom mapping cho KeHoachHocTap sang KeHoachHocTapResponse
        modelMapper.typeMap(KeHoachHocTap.class, KeHoachHocTapResponse.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getChuongTrinhDaoTao().getMaCTDT(), KeHoachHocTapResponse::setMaCTDT);
                    mapper.map(src -> src.getChuyenNganh().getMaChuyenNganh(), KeHoachHocTapResponse::setMaChuyenNganh);
                    mapper.map(src -> src.getChuyenNganh().getTenChuyenNganh(), KeHoachHocTapResponse::setTenChuyenNganh);
                    mapper.map(KeHoachHocTap::getMoTa, KeHoachHocTapResponse::setMoTa);
                });

        return modelMapper;
    }

}
