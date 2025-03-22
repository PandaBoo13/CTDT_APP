package com.example.CTDT_APP.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Khoa {
    @Id
    @Column(name = "MaKhoa", nullable = false, length = 21)
    private String maKhoa;

    @Column(name = "TenKhoa", length = 100)
    private String tenKhoa;

    @JsonIgnore
    @OneToMany(mappedBy = "khoa", fetch = FetchType.LAZY)
    private List<NganhDaoTao> nganhDaoTaos;
}