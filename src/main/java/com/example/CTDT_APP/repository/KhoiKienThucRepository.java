package com.example.CTDT_APP.repository;

import com.example.CTDT_APP.entity.KhoiKienThuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhoiKienThucRepository extends JpaRepository<KhoiKienThuc, String> {
    List<KhoiKienThuc> findAllByParentIsNull();
}