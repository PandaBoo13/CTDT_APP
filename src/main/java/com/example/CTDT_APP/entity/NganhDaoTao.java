package com.example.CTDT_APP.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class NganhDaoTao {
    @Id
    @Column(name = "MaNganh")
    private String maNganh;

    @Column(name = "TenNganhTV")
    private String tenNganhTV;

    @Column(name = "TenNganhTA")
    private String tenNganhTA;

    @Column(name = "NgayTao")
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private LocalDate ngayTao;

    @ManyToOne
    @JoinColumn(name = "MaKhoa")
    private Khoa khoa;
}