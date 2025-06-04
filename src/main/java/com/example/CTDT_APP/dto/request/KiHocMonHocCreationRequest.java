package com.example.CTDT_APP.dto.request;

import com.example.CTDT_APP.constant.LoaiMonHoc;
import lombok.Data;

@Data
public class KiHocMonHocCreationRequest {
    private String maMon;
    private LoaiMonHoc loaiMonHoc;
}
