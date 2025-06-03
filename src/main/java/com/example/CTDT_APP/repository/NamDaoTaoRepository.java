package com.example.CTDT_APP.repository;

import com.example.CTDT_APP.entity.NamDaoTao;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface NamDaoTaoRepository extends JpaRepository<NamDaoTao, Integer> {
}
