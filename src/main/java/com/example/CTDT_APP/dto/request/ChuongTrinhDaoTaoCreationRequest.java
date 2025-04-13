package com.example.CTDT_APP.dto.request;

import com.example.CTDT_APP.constant.TrangThai;
import lombok.Data;

import java.util.List;

@Data
public class ChuongTrinhDaoTaoCreationRequest {
    private String maCTDT;
    private String tenCTDT;
    private String moTa;
    private TrangThai trangThai;
    private String capBac;
    private String maHe;
    private String maNganh;
    private List<Integer> namDaoTao;
}
