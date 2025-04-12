package com.example.CTDT_APP.constant;

import lombok.Getter;

@Getter
public enum GioiTinh {
    NAM("Nam"), NU("Nữ");

    private final String value;

    GioiTinh(String value) {
        this.value = value;
    }
}
