package com.example.CTDT_APP.constant;

import lombok.Getter;

@Getter
public enum TrangThai {
    ACTIVE("Hoạt động"), INACTIVE("Ngừng hoạt động");

    private final String value;

    TrangThai(String value) {
        this.value = value;
    }
}
