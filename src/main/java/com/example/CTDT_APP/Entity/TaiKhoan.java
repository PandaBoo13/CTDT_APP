package com.example.CTDT_APP.Entity;

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
    private String maTaiKhoan;
    private String tenDangNhap;
    private String matKhau;
    private String maVaiTro;
}
