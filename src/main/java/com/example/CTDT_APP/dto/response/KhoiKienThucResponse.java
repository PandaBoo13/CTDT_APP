package com.example.CTDT_APP.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class KhoiKienThucResponse {
    private String maKhoi;
    private String tenKhoi;
    private String moTa;
    private List<KhoiKienThucResponse> children;
}
