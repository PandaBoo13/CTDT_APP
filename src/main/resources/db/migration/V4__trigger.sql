-- tg mon hoc
CREATE TRIGGER trg_before_delete_monhoc
    BEFORE DELETE ON MonHoc
    FOR EACH ROW
BEGIN
    IF EXISTS (SELECT 1 FROM QuanHeMonHoc WHERE MaMonLienQuan = OLD.MaMon) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Không thể xóa môn học đang có quan hệ tiên quyết';
    END IF;

    IF EXISTS (SELECT 1 FROM KiHoc_MonHoc WHERE MaMon = OLD.MaMon) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Không thể xóa môn học đã gán vào kỳ học';
    END IF;
END;

-- tg chuyen nganh
CREATE TRIGGER trg_before_delete_chuyennganh
    BEFORE DELETE ON ChuyenNganh
    FOR EACH ROW
BEGIN
    IF EXISTS (SELECT 1 FROM KeHoachHocTap WHERE MaChuyenNganh = OLD.MaChuyenNganh) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Không thể xóa chuyên ngành đã được dùng trong kế hoạch học tập';
    END IF;
END;

-- tg nganh dao tao
CREATE TRIGGER trg_before_delete_nganh
    BEFORE DELETE ON NganhDaoTao
    FOR EACH ROW
BEGIN
    IF EXISTS (SELECT 1 FROM ChuyenNganh WHERE MaNganh = OLD.MaNganh) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Không thể xóa ngành đào tạo có chuyên ngành phụ thuộc';
    END IF;

    IF EXISTS (SELECT 1 FROM ChuongTrinhDaoTao WHERE MaNganh = OLD.MaNganh) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Không thể xóa ngành đã được dùng trong CTĐT';
    END IF;
END;

CREATE TRIGGER trg_before_delete_bacdaotao
    BEFORE DELETE ON BacDaoTao
    FOR EACH ROW
BEGIN
    IF EXISTS (SELECT 1 FROM ChuongTrinhDaoTao WHERE CapBac = OLD.MaBac) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Không thể xoá bậc đào tạo đang có trong Chương trình đào tạo';
END IF;
END;

CREATE TRIGGER trg_before_delete_hedaotao
    BEFORE DELETE ON HeDaoTao
    FOR EACH ROW
BEGIN
    IF EXISTS (SELECT 1 FROM ChuongTrinhDaoTao WHERE MaHe = OLD.MaHe) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Không thể xoá hệ đào tạo đang có trong Chương trình đào tạo';
END IF;
END;

-- tg khoi kien thuc
CREATE TRIGGER trg_before_delete_khoikienthuc
    BEFORE DELETE ON KhoiKienThuc
    FOR EACH ROW
BEGIN
    -- Check nếu có khối con
    IF EXISTS (
        SELECT 1
        FROM KhoiKienThuc
        WHERE Parent = OLD.MaKhoi
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Không thể xóa khối kiến thức đang có khối con.';
    END IF;

    -- Check nếu có môn học thuộc khối
    IF EXISTS (
        SELECT 1
        FROM MonHoc
        WHERE MaKhoi = OLD.MaKhoi
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Không thể xóa khối kiến thức đang có môn học trực thuộc.';
    END IF;
END;

-- =============================================================================================
DELIMITER $$

CREATE TRIGGER trg_before_delete_kihoc_monhoc
    BEFORE DELETE ON KiHoc_MonHoc
    FOR EACH ROW
BEGIN
    DECLARE v_maKHHT VARCHAR(21);
    DECLARE v_ki_hien_tai INT;
    DECLARE v_has_dependent_subjects INT DEFAULT 0;

    -- Lấy thông tin về kế hoạch học tập và kỳ hiện tại của môn học sắp bị xóa
    SELECT kh.MaKHHT, kh.Ki
    INTO v_maKHHT, v_ki_hien_tai
    FROM KiHoc kh
    WHERE kh.MaKi = OLD.MaKi;

    -- Kiểm tra xem có môn học phụ thuộc nào trong cùng kế hoạch học tập không
    SELECT COUNT(*) INTO v_has_dependent_subjects
    FROM QuanHeMonHoc qh
             JOIN KiHoc_MonHoc kmh ON qh.MaMonChinh = kmh.MaMon
             JOIN KiHoc k ON kmh.MaKi = k.MaKi
    WHERE qh.MaMonLienQuan = OLD.MaMon  -- Môn đang bị xóa là môn tiên quyết
      AND k.MaKHHT = v_maKHHT           -- Cùng kế hoạch học tập
      AND k.Ki > v_ki_hien_tai
      AND qh.LoaiDieuKien IN ('TIEN_QUYET', 'HOC_TRUOC'); -- Chỉ kiểm tra điều kiện tiên quyết

    -- Nếu tìm thấy môn phụ thuộc, không cho phép xóa
    IF v_has_dependent_subjects > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Không thể xóa môn học này vì có môn học khác phụ thuộc vào nó trong cùng kế hoạch học tập.';
END IF;

END$$

DELIMITER ;