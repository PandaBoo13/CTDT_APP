package com.example.CTDT_APP.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class KhoiKienThuc {
    @Id
    @Column(name = "MaKhoi")
    private String maKhoi;

    @Column(name = "TenKhoi")
    private String tenKhoi;

    @Lob
    @Column(name = "MoTa")
    private String moTa;

    @JsonIgnore
    @OneToMany(mappedBy = "khoiKienThuc", fetch = FetchType.LAZY)
    private List<MonHoc> monHocs;
}