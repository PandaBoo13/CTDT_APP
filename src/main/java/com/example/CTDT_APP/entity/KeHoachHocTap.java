package com.example.CTDT_APP.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class KeHoachHocTap {
    @Id
    @Column(name = "MaKHHT", nullable = false, length = 21)
    private String maKHHT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaCTDT")
    private ChuongTrinhDaoTao maCTDT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaChuyenNganh")
    private ChuyenNganh maChuyenNganh;

    @Lob
    @Column(name = "MoTa")
    private String moTa;
}