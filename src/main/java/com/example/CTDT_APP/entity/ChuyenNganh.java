package com.example.CTDT_APP.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class ChuyenNganh {
    @Id
    @Column(name = "MaChuyenNganh", nullable = false, length = 21)
    private String maChuyenNganh;

    @Column(name = "TenChuyenNganh", length = 100)
    private String tenChuyenNganh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNganh")
    private NganhDaoTao maNganh;

    @OneToMany(mappedBy = "maChuyenNganh")
    private List<KeHoachHocTap> keHoachHocTaps;

}