package com.example.CTDT_APP.repository;

import com.example.CTDT_APP.entity.KeHoachHocTap;
import com.example.CTDT_APP.entity.KiHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KiHocRepository extends JpaRepository<KiHoc, String> {
    boolean existsByKiAndKeHoachHocTap(Integer ki, KeHoachHocTap keHoachHocTap);
    List<KiHoc> findAllByKeHoachHocTap(KeHoachHocTap keHoachHocTap);
}
