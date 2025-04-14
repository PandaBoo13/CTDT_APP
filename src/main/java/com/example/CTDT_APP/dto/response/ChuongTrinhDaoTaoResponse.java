package com.example.CTDT_APP.dto.response;

import com.example.CTDT_APP.constant.TrangThai;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChuongTrinhDaoTaoResponse {
    private String maCTDT;
    private String tenCTDT;
    private String moTa;
    private TrangThai trangThai;
    private List<KeHoachHocTapResponse> keHoachHocTaps;
    private List<NamCTDTResponse> namCtDtList;  // Danh sách năm của CTDT (join table Nam_CTDT)
}
