package com.example.CTDT_APP.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class HeDaoTao {
    @Id
    @Column(name = "MaHe")
    private String maHe;

    @Column(name = "TenHe")
    private String tenHe;

    @JsonIgnore
    @OneToMany(mappedBy = "heDaoTao", fetch = FetchType.LAZY)
    private List<ChuongTrinhDaoTao> chuongTrinhDaoTaos;
}