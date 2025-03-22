package com.example.CTDT_APP.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class QuanHeMonHoc {
    @EmbeddedId
    private QuanHeMonHocId id;

    @MapsId("maMonChinh")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MaMonChinh")
    private MonHoc maMonChinh;

    @MapsId("maMonLienQuan")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MaMonLienQuan")
    private MonHoc maMonLienQuan;

    @Column(name = "LoaiDieuKien")
    private String loaiDieuKien;
}