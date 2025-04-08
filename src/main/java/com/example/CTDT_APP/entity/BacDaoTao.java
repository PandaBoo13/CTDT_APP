package com.example.CTDT_APP.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class BacDaoTao {
    @Id
    @Column(name = "MaBac")
    private String maBac;

    @Column(name = "TenCapBac")
    private String tenCapBac;

    @Column(name = "ThoiGianDaoTao")
    private Float thoiGianDaoTao;

    @JsonIgnore
    @OneToMany(mappedBy = "capBac", fetch = FetchType.LAZY)
    private List<ChuongTrinhDaoTao> chuongTrinhDaoTaos;
}