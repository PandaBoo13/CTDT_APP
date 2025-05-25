package com.example.CTDT_APP.dto.response;

import com.example.CTDT_APP.constant.TrangThai;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChuongTrinhDaoTaoResponse {
    private String maCTDT;
    private String tenCTDT;
    private String moTa;
    private TrangThai trangThai;

    private NganhDaoTaoResponse nganhDaoTaoResponse;
    private HeDaoTaoResponse heDaoTaoResponse;
    private BacDaoTaoResponse bacDaoTaoResponse;

    // Danh sách các kế hoạch học tập của CTDT
    private List<KeHoachHocTapResponse> keHoachHocTaps;
}
