package com.example.CTDT_APP.Repository;

import com.example.CTDT_APP.Entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, String> {
    boolean existsByMaTaiKhoan(String maTaiKhoan);
    Optional<TaiKhoan> findByTenDangNhap( String tenDangNhap);
    boolean existsByTenDangNhap(String tenDangNhap);

}
