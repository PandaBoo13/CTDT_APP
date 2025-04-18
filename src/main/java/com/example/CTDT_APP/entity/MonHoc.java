package com.example.CTDT_APP.entity;

import com.example.CTDT_APP.constant.NgonNgu;
import com.example.CTDT_APP.constant.TrangThai;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class MonHoc {
    @Id
    @Column(name = "MaMon")
    private String maMon;

    @Column(name = "TenMon")
    private String tenMon;

    @Column(name = "SoTinChi")
    private Integer soTinChi;

    @Column(name = "SoTietLyThuyet")
    private Integer soTietLyThuyet;

    @Column(name = "SoTietBaiTap")
    private Integer soTietBaiTap;

    @Column(name = "SoTietThucHanh")
    private Integer soTietThucHanh;

    @Column(name = "SoTietTuHoc")
    private Integer soTietTuHoc;

    @Column(name = "NgonNguGiangDay")
    @Enumerated(EnumType.STRING)
    private NgonNgu ngonNguGiangDay;

    @Column(name = "TrangThai")
    @Enumerated(EnumType.STRING)
    private TrangThai trangThai;

    @JsonIgnore
    @ManyToMany(mappedBy = "monHocs")
    private List<KiHoc> kiHocs;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "MaKhoi")
    private KhoiKienThuc khoiKienThuc;

    @JsonIgnore
    @OneToMany(mappedBy = "monChinh")
    private List<QuanHeMonHoc> dsMonLienQuan;
}