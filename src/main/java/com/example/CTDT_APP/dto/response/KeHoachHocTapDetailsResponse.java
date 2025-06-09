package com.example.CTDT_APP.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class KeHoachHocTapDetailsResponse {
    private Integer ki;
    private Set<MonHocResponse> monHocs;
}
