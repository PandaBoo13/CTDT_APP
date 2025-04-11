package com.example.CTDT_APP.constant;

import lombok.Getter;

@Getter
public enum GioiTinh {
    MALE("Nam"), FEMALE("Nữ");

    private final String value;

    GioiTinh(String value) {
        this.value = value;
    }
}
