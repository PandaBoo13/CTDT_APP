package com.example.CTDT_APP.repository;

import com.example.CTDT_APP.entity.KhoiKienThuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhoiKienThucRepository extends JpaRepository<KhoiKienThuc, String> {
}