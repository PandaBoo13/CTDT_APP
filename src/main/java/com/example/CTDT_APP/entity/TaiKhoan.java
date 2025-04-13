package com.example.CTDT_APP.entity;

import com.example.CTDT_APP.constant.TrangThai;
import com.example.CTDT_APP.util.GenerateNanoID;
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
    @JoinColumn(name = "MaVaiTro")
    private VaiTro vaiTro;

    @Column(name = "TrangThai")
    @Enumerated(EnumType.STRING)
    private TrangThai trangThai;

    @OneToOne(mappedBy = "taiKhoan", cascade = CascadeType.ALL)
    private NhanVien nhanVien;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(vaiTro.getTenVaiTro()));
    }

    @Override
    public String getPassword() {
        return matKhau;
    }

    @Override
    public String getUsername() {
        return tenDangNhap;
    }

    @Override
    public boolean isAccountNonLocked() {
        return trangThai.name().equals(TrangThai.HOAT_DONG.name());
    }
}
