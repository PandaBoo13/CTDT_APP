package com.example.CTDT_APP.entity;

import com.example.CTDT_APP.util.GenerateNanoID;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class KeHoachHocTap {
    @Id
    @GenerateNanoID
    @Column(name = "MaKHHT")
    private String maKHHT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaCTDT")
    private ChuongTrinhDaoTao chuongTrinhDaoTao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaChuyenNganh")
    private ChuyenNganh chuyenNganh;

    @OneToMany(mappedBy = "keHoachHocTap")
    private List<KiHoc> kiHocs;

    @Lob
    @Column(name = "MoTa")
    private String moTa;
}