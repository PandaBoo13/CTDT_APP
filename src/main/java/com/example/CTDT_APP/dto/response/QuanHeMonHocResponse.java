package com.example.CTDT_APP.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuanHeMonHocResponse {
    private String maMonLienQuan;
    private String dieuKien;
}
