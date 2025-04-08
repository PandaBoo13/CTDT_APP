package com.example.CTDT_APP.repository;

import com.example.CTDT_APP.entity.ChuyenNganh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChuyenNganhRepository extends JpaRepository<ChuyenNganh, String> {
}
