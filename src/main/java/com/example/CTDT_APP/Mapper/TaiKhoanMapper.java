package com.example.CTDT_APP.Mapper;

import com.example.CTDT_APP.DTO.Request.TaiKhoanCreationRequest;
import com.example.CTDT_APP.Entity.TaiKhoan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaiKhoanMapper {
    TaiKhoan toTaiKhoan(TaiKhoanCreationRequest request);
}
