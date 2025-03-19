package com.example.CTDT_APP.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VaiTro {
    @Id
    private String maVaiTro;
    private String tenVaiTro;
    private String moTa;
}
