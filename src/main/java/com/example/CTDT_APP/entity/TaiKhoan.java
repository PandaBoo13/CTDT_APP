package com.example.CTDT_APP.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TaiKhoan")
public class TaiKhoan {
    @Id
    @Column(name = "MaTaiKhoan")
    private String maTaiKhoan;
    @Column(name = "TenDangNhap")
    private String tenDangNhap;
    @Column(name = "MatKhau")
    private String matKhau;
    @Column(name = "MaVaiTro")
    private String maVaiTro;
}
