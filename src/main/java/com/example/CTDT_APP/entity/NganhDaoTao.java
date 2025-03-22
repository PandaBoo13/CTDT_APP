package com.example.CTDT_APP.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class NganhDaoTao {
    @Id
    @Column(name = "MaNganh", nullable = false, length = 21)
    private String maNganh;

    @Column(name = "TenNganhTV", length = 100)
    private String tenNganhTV;

    @Column(name = "TenNganhTA", length = 100)
    private String tenNganhTA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaKhoa")
    private Khoa maKhoa;

}