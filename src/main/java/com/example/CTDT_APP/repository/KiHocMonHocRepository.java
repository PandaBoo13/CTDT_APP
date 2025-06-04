package com.example.CTDT_APP.repository;

import com.example.CTDT_APP.entity.KiHocMonHoc;
import com.example.CTDT_APP.entity.KihocMonhocId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KiHocMonHocRepository extends JpaRepository<KiHocMonHoc, KihocMonhocId> {
}