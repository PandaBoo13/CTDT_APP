package com.example.CTDT_APP.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class ChuongTrinhDaoTao {
    @Id
    @Column(name = "MaCTDT", nullable = false, length = 21)
    private String maCTDT;

    @Column(name = "TenCTDT", length = 100)
    private String tenCTDT;

    @Lob
    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "TrangThai", length = 21)
    private String trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CapBac")
    private BacDaoTao capBac;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaHe")
    private HeDaoTao maHe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNganh")
    private NganhDaoTao maNganh;

    @OneToMany(mappedBy = "maCTDT", fetch = FetchType.LAZY)
    private List<KeHoachHocTap> keHoachHocTaps;
}