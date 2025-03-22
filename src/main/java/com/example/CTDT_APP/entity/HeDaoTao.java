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
public class HeDaoTao {
    @Id
    @Column(name = "MaHe", nullable = false, length = 21)
    private String maHe;

    @Column(name = "TenHe", length = 100)
    private String tenHe;

    @OneToMany(mappedBy = "maHe")
    private List<ChuongTrinhDaoTao> chuongTrinhDaoTaos;

}