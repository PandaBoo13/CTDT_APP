package com.example.CTDT_APP.constant;

import lombok.Getter;

@Getter
public enum TrangThai {
    HOAT_DONG("Hoạt động"), NGUNG_HOAT_DONG("Ngừng hoạt động");

    private final String value;

    TrangThai(String value) {
        this.value = value;
    }
}
