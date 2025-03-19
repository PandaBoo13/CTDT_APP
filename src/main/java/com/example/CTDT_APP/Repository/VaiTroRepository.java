package com.example.CTDT_APP.Repository;

import com.example.CTDT_APP.Entity.VaiTro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaiTroRepository extends JpaRepository<VaiTro, String> {
}
