package com.example.CTDT_APP.dto.request;

import com.example.CTDT_APP.constant.DieuKienMonHoc;
import lombok.Data;

@Data
public class QuanHeMonHocRequest {
    private String maMon;
    private DieuKienMonHoc dieuKien;
}
