package com.example.CTDT_APP.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BacDaoTaoResponse {
    private String maBac;
    private String tenCapBac;
    private Float thoiGianDaoTao;
}
