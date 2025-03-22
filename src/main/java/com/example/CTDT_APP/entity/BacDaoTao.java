package com.example.CTDT_APP.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class BacDaoTao {
    @Id
    @Column(name = "MaBac", nullable = false, length = 21)
    private String maBac;

    @Column(name = "TenCapBac", length = 100)
    private String tenCapBac;

    @Column(name = "ThoiGianDaoTao")
    private Float thoiGianDaoTao;

    @OneToMany(mappedBy = "capBac", fetch = FetchType.LAZY)
    private List<ChuongTrinhDaoTao> chuongTrinhDaoTao;
}