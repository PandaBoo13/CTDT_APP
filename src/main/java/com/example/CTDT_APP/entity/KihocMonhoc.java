package com.example.CTDT_APP.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "KiHoc_MonHoc")
public class KihocMonhoc {
    @EmbeddedId
    private KihocMonhocId id;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MaKi", nullable = false)
    private KiHoc maKi;

}