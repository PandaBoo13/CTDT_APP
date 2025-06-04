package com.example.CTDT_APP.entity;

import com.example.CTDT_APP.constant.LoaiMonHoc;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "KiHoc_MonHoc")
public class KiHocMonHoc {
    @EmbeddedId
    private KihocMonhocId id;

    @ManyToOne
    @MapsId("maKi")
    @JoinColumn(name = "MaKi")
    private KiHoc kiHoc;

    @ManyToOne
    @MapsId("maMon")
    @JoinColumn(name = "MaMon")
    private MonHoc monHoc;

    @Column(name = "LoaiMonHoc")
    @Enumerated(EnumType.STRING)
    private LoaiMonHoc loaiMonHoc;
}
