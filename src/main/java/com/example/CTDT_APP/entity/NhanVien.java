package com.example.CTDT_APP.entity;


import com.example.CTDT_APP.constant.GioiTinh;
import com.example.CTDT_APP.util.GenerateNanoID;
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
    @GenerateNanoID
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

    @Column(name = "GioiTinh")
    @Enumerated(EnumType.STRING)
    private GioiTinh gioiTinh;

    @OneToOne
    @JoinColumn(name = "MaTaiKhoan")
    private TaiKhoan taiKhoan;
}
