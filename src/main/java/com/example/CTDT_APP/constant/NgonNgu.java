package com.example.CTDT_APP.constant;

import lombok.Getter;

@Getter
public enum NgonNgu {
    TIENG_VIET("Tiếng Việt"),
    TIENG_ANH("Tiếng Anh");

    private final String value;

    NgonNgu(String value) {
        this.value = value;
    }
}
