package com.example.CTDT_APP.repository;

import com.example.CTDT_APP.entity.BacDaoTao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacDaoTaoRepository extends JpaRepository<BacDaoTao, String> {
}
