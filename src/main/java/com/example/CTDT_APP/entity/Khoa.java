package com.example.CTDT_APP.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Khoa {
    @Id
    @Column(name = "MaKhoa")
    private String maKhoa;

    @Column(name = "TenKhoa")
    private String tenKhoa;

    @Column(name = "NgayTao")
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private LocalDate ngayTao;

    @JsonIgnore
    @OneToMany(mappedBy = "khoa", fetch = FetchType.LAZY)
    private List<NganhDaoTao> nganhDaoTaos;
}