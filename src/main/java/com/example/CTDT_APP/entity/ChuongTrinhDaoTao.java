package com.example.CTDT_APP.entity;

import com.example.CTDT_APP.constant.TrangThai;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class ChuongTrinhDaoTao {
    @Id
    @Column(name = "MaCTDT")
    private String maCTDT;

    @Column(name = "TenCTDT")
    private String tenCTDT;

    @Lob
    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "TrangThai")
    @Enumerated(EnumType.STRING)
    private TrangThai trangThai;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CapBac")
    private BacDaoTao capBac;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaHe")
    private HeDaoTao maHe;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNganh")
    private NganhDaoTao maNganh;

    @OneToMany(mappedBy = "chuongTrinhDaoTao", fetch = FetchType.LAZY)
    private List<KeHoachHocTap> keHoachHocTaps;
}