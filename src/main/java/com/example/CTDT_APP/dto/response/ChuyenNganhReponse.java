package com.example.CTDT_APP.dto.response;

import lombok.Data;

@Data
public class ChuyenNganhReponse {
    private String maChuyenNganh;
    private String tenChuyenNganh;
    private NganhDaoTaoResponse nganhDaoTao;
}
