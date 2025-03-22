package com.example.CTDT_APP.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "capBac")
    private List<ChuongTrinhDaoTao> chuongTrinhDaoTao;

}