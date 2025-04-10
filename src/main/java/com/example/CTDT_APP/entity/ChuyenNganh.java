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
public class ChuyenNganh {
    @Id
    @Column(name = "MaChuyenNganh")
    private String maChuyenNganh;

    @Column(name = "TenChuyenNganh")
    private String tenChuyenNganh;

    @ManyToOne
    @JoinColumn(name = "MaNganh")
    private NganhDaoTao nganhDaoTao;

    @Column(name = "NgayTao")
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private LocalDate ngayTao;

    @JsonIgnore
    @OneToMany(mappedBy = "maChuyenNganh", fetch = FetchType.LAZY)
    private List<KeHoachHocTap> keHoachHocTaps;
}