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

-- tg khoa
CREATE TRIGGER trg_before_delete_khoa
    BEFORE DELETE ON Khoa
    FOR EACH ROW
BEGIN
    IF EXISTS (SELECT 1 FROM NganhDaoTao WHERE MaKhoa = OLD.MaKhoa) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Không thể xóa khoa đang có ngành đào tạo';
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

