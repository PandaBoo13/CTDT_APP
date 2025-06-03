package com.example.CTDT_APP.constant;

import lombok.Getter;

@Getter
public enum LoaiMonHoc {
    BAT_BUOC("Bắt buộc"),
    TU_CHON("Tự chọn"),
    THAY_THE_TOT_NGHIEP("Thay thế tốt nghiệp");

    private final String value;

    LoaiMonHoc(String value) {
        this.value = value;
    }
}
