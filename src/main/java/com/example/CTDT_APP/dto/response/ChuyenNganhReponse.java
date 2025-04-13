package com.example.CTDT_APP.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ChuyenNganhReponse {
    private String maChuyenNganh;
    private String tenChuyenNganh;
    private NganhDaoTaoResponse nganhDaoTao;
    private LocalDate ngayTao;
}
