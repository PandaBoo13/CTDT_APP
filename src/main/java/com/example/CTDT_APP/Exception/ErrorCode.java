package com.example.CTDT_APP.Exception;

import lombok.*;

@Getter

public enum ErrorCode {
    TAIKHOAN_EXISTED(1001, "Tai khoan da ton tai"),
    TENDANGNHAP_EXISTED(1002, "Ten dang nhap da ton tai"),
    TAIKHOAN_NOT_EXSITED(1003,"Tai khoan khong ton tai"),
    UNAUTHENTICATED(1004, "Sai mat khau");
    private int code;
    private String message;


    ErrorCode(int code, String message){
        this.code=code;
        this.message=message;
    }

}
