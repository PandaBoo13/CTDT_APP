package com.example.CTDT_APP.DTO.Respone;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationRespone {
    Boolean authenticated;
    String token;
}
