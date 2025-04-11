package com.example.CTDT_APP.mapper;

import com.example.CTDT_APP.dto.request.TaiKhoanRegisterRequest;
import com.example.CTDT_APP.entity.TaiKhoan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaiKhoanMapper {
    TaiKhoan toTaiKhoan(TaiKhoanRegisterRequest request);
}
