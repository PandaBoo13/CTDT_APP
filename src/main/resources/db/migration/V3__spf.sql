
-- Thêm môn học vào kỳ học
DELIMITER //

CREATE PROCEDURE sp_them_mon_vao_kihoc (
    IN p_maKi VARCHAR(50),
    IN p_maMon VARCHAR(50),
    IN p_loaiMonHoc VARCHAR(50)
)
BEGIN
    DECLARE v_has_invalid_prereq INT;
    DECLARE v_maKHHT VARCHAR(50);
    DECLARE v_exists_in_khht INT;

    -- Lấy MaKHHT từ p_maKi
    SELECT MaKHHT INTO v_maKHHT FROM KiHoc WHERE MaKi = p_maKi;

    -- Kiểm tra môn học đã tồn tại trong bất kỳ kỳ nào của cùng kế hoạch học tập chưa
    SELECT COUNT(*) INTO v_exists_in_khht
    FROM KiHoc_MonHoc kmh
             JOIN KiHoc k ON kmh.MaKi = k.MaKi
    WHERE k.MaKHHT = v_maKHHT AND kmh.MaMon = p_maMon;

    IF v_exists_in_khht > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Môn học đã tồn tại trong kế hoạch học tập này.';
    ELSE
        -- Kiểm tra điều kiện tiên quyết
        SELECT COUNT(*)
        INTO v_has_invalid_prereq
        FROM QuanHeMonHoc q
        WHERE q.MaMonChinh = p_maMon
          AND NOT EXISTS (
            SELECT 1
            FROM KiHoc_MonHoc km
                     JOIN KiHoc k ON k.MaKi = km.MaKi
            WHERE km.MaMon = q.MaMonLienQuan
              AND k.MaKHHT = v_maKHHT
              AND k.Ki < (SELECT Ki FROM KiHoc WHERE MaKi = p_maKi)
        );

        IF v_has_invalid_prereq > 0 THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Môn tiên quyết chưa được học ở các kỳ trước.';
        ELSE
            -- Thêm môn học vào kỳ học
            INSERT INTO KiHoc_MonHoc (MaKi, MaMon, LoaiMonHoc)
            VALUES (p_maKi, p_maMon, p_loaiMonHoc);
        END IF;
    END IF;
END;
//

DELIMITER ;









