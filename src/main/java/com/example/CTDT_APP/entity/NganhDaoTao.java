package com.example.CTDT_APP.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class NganhDaoTao {
    @Id
    @Column(name = "MaNganh")
    private String maNganh;

    @Column(name = "TenNganhTV")
    private String tenNganhTV;

    @Column(name = "TenNganhTA")
    private String tenNganhTA;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaKhoa")
    private Khoa khoa;
}