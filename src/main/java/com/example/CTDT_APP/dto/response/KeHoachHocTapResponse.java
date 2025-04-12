package com.example.CTDT_APP.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Builder
public class KeHoachHocTapResponse {
    private String maKHHT;
    private String maCTDT;
    private String maChuyenNganh;
    private String tenChuyenNganh;
    private String moTa;
}
