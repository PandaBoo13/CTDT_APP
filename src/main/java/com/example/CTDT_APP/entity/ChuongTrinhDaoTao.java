package com.example.CTDT_APP.entity;

import com.example.CTDT_APP.constant.TrangThai;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChuongTrinhDaoTao {
    @Id
    @Column(name = "MaCTDT")
    private String maCTDT;

    @Column(name = "TenCTDT")
    private String tenCTDT;

    @Lob
    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "TrangThai")
    @Enumerated(EnumType.STRING)
    private TrangThai trangThai;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CapBac")
    private BacDaoTao bacDaoTao;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaHe")
    private HeDaoTao heDaoTao;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNganh")
    private NganhDaoTao nganhDaoTao;

    @JsonIgnore
    @OneToMany(mappedBy = "chuongTrinhDaoTao", fetch = FetchType.LAZY)
    private List<KeHoachHocTap> keHoachHocTaps;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "Nam_CTDT",
            joinColumns = @JoinColumn(name = "MaCTDT"),
            inverseJoinColumns = @JoinColumn(name = "Nam")
    )
    private List<NamDaoTao> namDaoTaos;
}