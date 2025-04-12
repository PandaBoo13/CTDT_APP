package com.example.CTDT_APP.repository;

import com.example.CTDT_APP.entity.ChuongTrinhDaoTao;
import com.example.CTDT_APP.entity.KeHoachHocTap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeHoachHocTapRepository extends JpaRepository<KeHoachHocTap, String> {
    List<KeHoachHocTap> findAllByChuongTrinhDaoTao(ChuongTrinhDaoTao chuongTrinhDaoTao);
}
