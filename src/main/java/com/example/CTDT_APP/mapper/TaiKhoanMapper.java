package com.example.CTDT_APP.mapper;

import com.example.CTDT_APP.dto.request.TaiKhoanCreationRequest;
import com.example.CTDT_APP.entity.TaiKhoan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaiKhoanMapper {
    TaiKhoan toTaiKhoan(TaiKhoanCreationRequest request);
}
