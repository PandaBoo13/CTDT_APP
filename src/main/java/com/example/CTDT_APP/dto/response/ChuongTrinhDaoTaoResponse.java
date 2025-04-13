package com.example.CTDT_APP.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChuongTrinhDaoTaoResponse {
    private String maCTDT;
    private String tenCTDT;
    private String moTa;
    private String trangThai;
    private String capBac;
    private String heDaoTao;
    private String nganhDaoTao;
}
