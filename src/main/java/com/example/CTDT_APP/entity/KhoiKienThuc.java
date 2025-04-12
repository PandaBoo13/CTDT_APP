package com.example.CTDT_APP.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Parent")
    private KhoiKienThuc parent;

    @JsonIgnore
    @OneToMany(mappedBy = "parent")
    private List<KhoiKienThuc> children = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "khoiKienThuc", fetch = FetchType.LAZY)
    private List<MonHoc> monHocs;
}