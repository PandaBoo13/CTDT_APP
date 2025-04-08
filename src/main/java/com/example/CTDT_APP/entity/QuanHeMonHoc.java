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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MaMonChinh")
    private MonHoc monHocChinh;

    @MapsId("maMonLienQuan")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MaMonLienQuan")
    private MonHoc monHocLienQuan;

    @Column(name = "LoaiDieuKien")
    private DieuKienMonHoc loaiDieuKien;
}