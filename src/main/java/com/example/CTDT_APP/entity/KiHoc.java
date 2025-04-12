package com.example.CTDT_APP.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class KiHoc {
    @Id
    @Column(name = "MaKi")
    private String maKi;

    @Column(name = "Ki")
    private Integer ki;

    @ManyToOne
    @JoinColumn(name = "MaKHHT")
    private KeHoachHocTap keHoachHocTap;

    @ManyToMany
    @JoinTable(
            name = "KiHoc_MonHoc",
            joinColumns = @JoinColumn(name = "MaKi"),
            inverseJoinColumns = @JoinColumn(name = "MaMon")
    )
    private Set<MonHoc> monHocs = new HashSet<>();
}