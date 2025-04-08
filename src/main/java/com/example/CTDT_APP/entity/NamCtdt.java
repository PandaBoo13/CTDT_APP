package com.example.CTDT_APP.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Nam_CTDT")
public class NamCtdt {
    @EmbeddedId
    private NamCtdtId id;

    @MapsId("nam")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Nam")
    private NamDaoTao nam;

    @MapsId("maCTDT")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MaCTDT")
    private ChuongTrinhDaoTao chuongTrinhDaoTao;
}