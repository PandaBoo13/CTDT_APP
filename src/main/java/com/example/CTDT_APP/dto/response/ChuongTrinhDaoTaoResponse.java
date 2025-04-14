package com.example.CTDT_APP.dto.response;

import com.example.CTDT_APP.constant.TrangThai;
import lombok.Data;

import java.util.List;

@Data
public class ChuongTrinhDaoTaoResponse {
    private String maCTDT;
    private String tenCTDT;
    private String moTa;
    private TrangThai trangThai;

    // Danh sách các kế hoạch học tập của CTDT
    private List<KeHoachHocTapResponse> keHoachHocTaps;
}
