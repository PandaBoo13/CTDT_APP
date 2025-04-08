package com.example.CTDT_APP.repository;

import com.example.CTDT_APP.entity.QuanHeMonHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MHLQRepository extends JpaRepository<QuanHeMonHoc, String> {
}
