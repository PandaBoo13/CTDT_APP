package com.example.CTDT_APP.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoiMatKhauRequest {
    private String matKhauCu;
    private String matKhauMoi;
}
