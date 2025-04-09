package com.example.CTDT_APP.entity;

import com.example.CTDT_APP.util.GenerateNanoID;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TaiKhoan")
public class TaiKhoan implements UserDetails {
    @Id
    @GenerateNanoID
    @Column(name = "MaTaiKhoan")
    private String maTaiKhoan;
    @Column(name = "TenDangNhap")
    private String tenDangNhap;
    @Column(name = "MatKhau")
    private String matKhau;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "MaVaiTro")
    private VaiTro vaiTro;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (vaiTro == null || vaiTro.getMaVaiTro() == null) {
            return List.of(); // hoặc throw exception, hoặc log cảnh báo
        }
        return List.of(new SimpleGrantedAuthority(vaiTro.getMaVaiTro()));
    }

    @Override
    public String getPassword() {
        return matKhau;
    }

    @Override
    public String getUsername() {
        return tenDangNhap;
    }
}
