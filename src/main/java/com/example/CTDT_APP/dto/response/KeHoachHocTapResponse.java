package com.example.CTDT_APP.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeHoachHocTapResponse {
    private String maKHHT;
    private String maCTDT;
    private String maChuyenNganh;
    private String tenChuyenNganh;
    private String moTa;
}
