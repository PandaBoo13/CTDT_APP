package com.example.CTDT_APP.dto.response;

import com.example.CTDT_APP.constant.TrangThai;
import com.example.CTDT_APP.entity.BacDaoTao;
import com.example.CTDT_APP.entity.HeDaoTao;
import com.example.CTDT_APP.entity.NamDaoTao;
import com.example.CTDT_APP.entity.NganhDaoTao;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class ChuongTrinhDaoTaoResponse {
    private String maCTDT;
    private String tenCTDT;
    private String moTa;
    private HeDaoTaoResponse heDaoTao;
    private BacDaoTaoResponse bacDaoTao;
    private NganhDaoTaoResponse nganhDaoTao;
    private TrangThai trangThai;
    private List<Integer> namDaoTao;
}
