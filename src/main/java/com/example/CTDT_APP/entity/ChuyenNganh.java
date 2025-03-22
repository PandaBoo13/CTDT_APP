package com.example.CTDT_APP.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class ChuyenNganh {
    @Id
    @Column(name = "MaChuyenNganh")
    private String maChuyenNganh;

    @Column(name = "TenChuyenNganh")
    private String tenChuyenNganh;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNganh")
    private NganhDaoTao maNganh;

    @JsonIgnore
    @OneToMany(mappedBy = "maChuyenNganh", fetch = FetchType.LAZY)
    private List<KeHoachHocTap> keHoachHocTaps;
}