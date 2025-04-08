package com.example.CTDT_APP.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class KiHoc {
    @Id
    @Column(name = "MaKi")
    private String maKi;

    @Column(name = "Ki")
    private Integer ki;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaKHHT")
    private KeHoachHocTap keHoachHocTap;

    @JsonIgnore
    @OneToMany(mappedBy = "maKi", fetch = FetchType.LAZY)
    private List<KihocMonhoc> kihocMonhocs;
}