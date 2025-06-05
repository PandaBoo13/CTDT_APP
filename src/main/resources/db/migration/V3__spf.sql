-- Xoa CTDT
DELIMITER $$

CREATE PROCEDURE sp_delete_chuong_trinh_dao_tao(IN p_MaCTDT VARCHAR(21))
BEGIN
    -- Bắt đầu giao dịch
START TRANSACTION;

-- Kiểm tra sự tồn tại của MaCTDT
IF NOT EXISTS (SELECT 1 FROM ChuongTrinhDaoTao WHERE MaCTDT = p_MaCTDT) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Chuong trinh dao tao khong ton tai.';
END IF;

    -- Xoá các môn học trong từng kỳ thuộc kế hoạch học tập của chương trình
    DELETE KHMH
    FROM KiHoc_MonHoc KHMH
    JOIN KiHoc KH ON KH.MaKi = KHMH.MaKi
    JOIN KeHoachHocTap KHHT ON KH.MaKHHT = KHHT.MaKHHT
    WHERE KHHT.MaCTDT = p_MaCTDT;

    -- Xoá các kỳ học thuộc kế hoạch học tập của chương trình
    DELETE KH
    FROM KiHoc KH
    JOIN KeHoachHocTap KHHT ON KH.MaKHHT = KHHT.MaKHHT
    WHERE KHHT.MaCTDT = p_MaCTDT;

    -- Xoá các kế hoạch học tập thuộc chương trình
DELETE FROM KeHoachHocTap
WHERE MaCTDT = p_MaCTDT;

-- Xoá các liên kết năm - CTĐT
DELETE FROM Nam_CTDT
WHERE MaCTDT = p_MaCTDT;

-- Cuối cùng xoá chương trình đào tạo
DELETE FROM ChuongTrinhDaoTao
WHERE MaCTDT = p_MaCTDT;

-- Commit nếu mọi thứ thành công
COMMIT;
END$$

DELIMITER ;

-- Xoa KHHT
DELIMITER $$

CREATE PROCEDURE sp_delete_ke_hoach_hoc_tap(IN p_MaKHHT VARCHAR(21))
BEGIN
START TRANSACTION;

-- Kiểm tra KHHT tồn tại
IF NOT EXISTS (SELECT 1 FROM KeHoachHocTap WHERE MaKHHT = p_MaKHHT) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ke hoach hoc tap khong ton tai.';
END IF;

    -- Xoá các môn học trong kỳ học thuộc KHHT
    DELETE KHMH
    FROM KiHoc_MonHoc KHMH
    JOIN KiHoc KH ON KHMH.MaKi = KH.MaKi
    WHERE KH.MaKHHT = p_MaKHHT;

    -- Xoá các kỳ học thuộc KHHT
DELETE FROM KiHoc
WHERE MaKHHT = p_MaKHHT;

-- Xoá KHHT
DELETE FROM KeHoachHocTap
WHERE MaKHHT = p_MaKHHT;

COMMIT;
END$$

DELIMITER ;

-- Xoa KiHoc
DELIMITER $$

CREATE PROCEDURE sp_delete_ki_hoc(IN p_MaKi VARCHAR(21))
BEGIN
START TRANSACTION;

-- Kiểm tra kỳ học tồn tại
IF NOT EXISTS (SELECT 1 FROM KiHoc WHERE MaKi = p_MaKi) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ki hoc khong ton tai.';
END IF;

    -- Xoá các môn học trong kỳ học
DELETE FROM KiHoc_MonHoc
WHERE MaKi = p_MaKi;

-- Xoá kỳ học
DELETE FROM KiHoc
WHERE MaKi = p_MaKi;

COMMIT;
END$$

DELIMITER ;


-- Thêm môn học vào kỳ học
DELIMITER //

CREATE PROCEDURE sp_them_mon_vao_kihoc (
    IN p_maKi VARCHAR(50),
    IN p_maMon VARCHAR(50),
    IN p_loaiMonHoc VARCHAR(50)
)
BEGIN
    DECLARE v_has_invalid_prereq INT;

    -- Kiểm tra môn học có điều kiện tiên quyết không
SELECT COUNT(*)
INTO v_has_invalid_prereq
FROM QuanHeMonHoc q
WHERE q.MaMonChinh = p_maMon
  AND NOT EXISTS (
    SELECT 1
    FROM KiHoc_MonHoc km
    JOIN KiHoc k ON k.MaKi = km.MaKi
    WHERE km.MaMon = q.MaMonLienQuan
      AND k.MaKHHT = (SELECT MaKHHT FROM KiHoc WHERE MaKi = p_maKi)
      AND k.Ki < (SELECT Ki FROM KiHoc WHERE MaKi = p_maKi)
);

IF v_has_invalid_prereq > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Môn tiên quyết chưa được học ở các kỳ trước.';
ELSE
        INSERT INTO KiHoc_MonHoc (MaKi, MaMon, LoaiMonHoc)
        VALUES (p_maKi, p_maMon, p_loaiMonHoc);
END IF;
END;
//

DELIMITER ;








