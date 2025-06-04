package com.example.CTDT_APP.entity;

import com.example.CTDT_APP.util.GenerateNanoID;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "KiHoc")
@Entity
public class KiHoc {
    @Id
    @GenerateNanoID
    @Column(name = "MaKi")
    private String maKi;

    @Column(name = "Ki")
    private Integer ki;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "MaKHHT")
    private KeHoachHocTap keHoachHocTap;

    @JsonIgnore
    @ManyToMany()
    @JoinTable(
            name = "KiHoc_MonHoc",
            joinColumns = @JoinColumn(name = "MaKi"),
            inverseJoinColumns = @JoinColumn(name = "MaMon")
    )
    private Set<MonHoc> monHocs = new HashSet<>();
}