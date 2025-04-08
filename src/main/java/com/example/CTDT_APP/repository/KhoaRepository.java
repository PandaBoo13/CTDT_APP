package com.example.CTDT_APP.repository;

import com.example.CTDT_APP.entity.Khoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhoaRepository extends JpaRepository<Khoa, String> {
}
