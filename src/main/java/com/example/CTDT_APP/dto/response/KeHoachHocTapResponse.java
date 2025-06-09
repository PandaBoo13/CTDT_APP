package com.example.CTDT_APP.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeHoachHocTapResponse {
    private String maKHHT;
    private ChuyenNganhBriefResponse chuyenNganh;
    private String moTa;
}
