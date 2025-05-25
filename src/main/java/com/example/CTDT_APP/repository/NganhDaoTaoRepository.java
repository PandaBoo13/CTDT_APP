package com.example.CTDT_APP.repository;

import com.example.CTDT_APP.entity.NganhDaoTao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NganhDaoTaoRepository extends JpaRepository<NganhDaoTao, String> {

    Optional<NganhDaoTao> findByTenNganhTV(String tenNganh);
}
