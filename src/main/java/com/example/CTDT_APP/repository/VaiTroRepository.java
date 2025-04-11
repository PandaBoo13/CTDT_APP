package com.example.CTDT_APP.repository;

import com.example.CTDT_APP.entity.VaiTro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VaiTroRepository extends JpaRepository<VaiTro, String> {
    Optional<VaiTro> findByTenVaiTro(String tenVaiTro);
}
