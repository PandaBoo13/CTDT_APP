package com.example.CTDT_APP.repository;

import com.example.CTDT_APP.entity.HeDaoTao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeDaoTaoRepository extends JpaRepository<HeDaoTao, String> {
}
