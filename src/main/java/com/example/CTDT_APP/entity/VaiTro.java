package com.example.CTDT_APP.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VaiTro {
    @Id
    @Column(name = "MaVaiTro")
    private String maVaiTro;
    @Column(name = "TenVaiTro")
    private String tenVaiTro;
    @Column(name = "MoTa")
    private String moTa;

    @OneToMany(mappedBy = "vaiTro")
    @JsonManagedReference
    private List<TaiKhoan> taiKhoans;
}
