package com.example.CTDT_APP.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NhanVien {
    @Id
    private String MaNhanVien;
    @Column( nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String soDienThoai;
    @Column(nullable = false)
    private String hoTen;
    private LocalDate ngayThangNamSinh;
    @Column(unique = true, nullable = false)
    private String maTaikhoan;
}
