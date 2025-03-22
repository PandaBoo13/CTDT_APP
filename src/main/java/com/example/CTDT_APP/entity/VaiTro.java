package com.example.CTDT_APP.entity;

import jakarta.persistence.Column;
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
    @Column(name = "MaVaiTro")
    private String maVaiTro;
    @Column(name = "TenVaiTro")
    private String tenVaiTro;
    @Column(name = "MoTa")
    private String moTa;
}
