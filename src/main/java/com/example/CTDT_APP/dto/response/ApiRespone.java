package com.example.CTDT_APP.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiRespone<T> {
    private int code;
    private String message;
    private T result;

}
