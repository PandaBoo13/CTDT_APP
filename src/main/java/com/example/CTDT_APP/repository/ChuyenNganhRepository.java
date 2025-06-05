package com.example.CTDT_APP.repository;

import com.example.CTDT_APP.entity.ChuyenNganh;
import com.example.CTDT_APP.entity.NganhDaoTao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ChuyenNganhRepository extends JpaRepository<ChuyenNganh, String> {
    List<ChuyenNganh> findAllByNganhDaoTao(NganhDaoTao nganhDaoTao);
}
