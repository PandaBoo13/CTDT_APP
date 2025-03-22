package com.example.CTDT_APP.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    @Column(name = "MaNhanVien")
    private String MaNhanVien;
    @Column(name = "Email")
    private String email;
    @Column(name = "SoDienThoai")
    private String soDienThoai;
    @Column(name = "HoTen")
    private String hoTen;
    @Column(name = "NgayThangNamSinh")
    private LocalDate ngayThangNamSinh;
    @Column(name = "MaTaiKhoan")
    private String maTaikhoan;
}
