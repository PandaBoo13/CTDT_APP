package com.example.CTDT_APP.constant;

import lombok.Getter;

@Getter
public enum DieuKienMonHoc {
    TIEN_QUYET("Môn tiên quyết"), SONG_HANH("Môn song hành"), HOC_TRUOC("Môn học trước");

    private final String value;

    DieuKienMonHoc(String value) {
        this.value = value;
    }
}
