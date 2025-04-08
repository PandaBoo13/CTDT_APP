package com.example.CTDT_APP.entity;

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
    private String ngonNguGiangDay;
    @Column(name = "TrangThai")
    @Enumerated(EnumType.STRING)
    private TrangThai trangThai;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaKhoi")
    private KhoiKienThuc khoiKienThuc;
    @JsonIgnore
    @OneToMany(mappedBy = "monHocLienQuan")
    private List<QuanHeMonHoc> quanHeMonHocs;
}