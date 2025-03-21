package com.example.CTDT_APP.dto.response;

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
