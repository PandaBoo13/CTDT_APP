package com.example.CTDT_APP.entity;

import com.example.CTDT_APP.constant.DieuKienMonHoc;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaMonChinh")
    private MonHoc monChinh;

    @MapsId("maMonLienQuan")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaMonLienQuan")
    private MonHoc monLienQuan;

    @Column(name = "LoaiDieuKien")
    @Enumerated(EnumType.STRING)
    private DieuKienMonHoc loaiDieuKien;
}