-- Data Khoa
insert into Khoa (MaKhoa, TenKhoa)
values
('CNTT2', 'Khoa Công nghệ thông tin 2');

# Data Vai Tro
insert into VaiTro (MaVaiTro, TenVaiTro)
values
    ('EMPLOYEE', 'ROLE_EMPLOYEE'),
    ('ADMIN', 'ROLE_ADMIN');

-- Data KhoiKienThuc
INSERT INTO KhoiKienThuc (MaKhoi, TenKhoi, MoTa, Parent)
VALUES
('GDDC', 'Khối kiến thức giáo dục đại cương', 'Mô tả ...', NULL),
('KTGDCN', 'Khối kiến thức giáo dục chuyên nghiệp', 'Mô tả ...', NULL),
('TTVTN', 'Thực tập và tốt nghiệp', 'Mô tả ...', NULL),
('KTC', 'Khối kiến thức chung', 'Mô tả ...', 'GDDC'),
('KTCB', 'Khối kiến thức cơ bản nhóm ngành', 'Mô tả ...', 'GDDC'),
('KTCS', 'Khối kiến thức cơ sở của khối ngành và ngành', 'Mô tả ...', 'KTGDCN'),
('KTCN', 'Kiến thức chuyên ngành', 'Mô tả ...', 'KTGDCN'),
('HPTC', 'Học phần tự chọn (Kiến thức chuyên ngành)', 'Mô tả ...', 'KTCN'),
('HPTTTN', 'Học phần thay thế tốt nghiệp', 'Mô tả ...', 'KTCN');


insert into TaiKhoan (MaTaiKhoan, TenDangNhap,MatKhau, MaVaiTro)
values
('admin', 'admin', '$2a$10$Eof5DH8meJQDpnVp2GgA9.qjdHiP0IiAUr0SEZwozU8oWgpfjbiZ.', 'ADMIN'),
('employee', 'employee', '$2a$10$Eof5DH8meJQDpnVp2GgA9.qjdHiP0IiAUr0SEZwozU8oWgpfjbiZ.', 'EMPLOYEE');
# password: 123456

insert into NhanVien (MaNhanVien, HoTen, NgayThangNamSinh, SoDienThoai, Email, GioiTinh, MaTaiKhoan)
values
('123', 'Nguyen Van A', '2000/01/20', '09123456789', 'employee@gmail.com', 'NAM','employee');


# Data Bac Dao Tao
insert into BacDaoTao (MaBac, TenCapBac, ThoiGianDaoTao)
values
('DH', 'Đại học', 4.5),
('TH', 'Thạc sĩ', 2),
('TS', 'Tiến sĩ', 4);

# Data He Dao Tao
insert into HeDaoTao (MaHe, TenHe)
values
('CQ', 'Chính quy'),
('VHVL', 'Vừa làm vừa học'),
('TX', 'Đào tạo từ xa');

# Data Nganh Dao Tao
INSERT INTO NganhDaoTao (MaNganh, TenNganhTV, TenNganhTA, MaKhoa)
VALUES
('CNTT', 'Công nghệ thông tin', 'Information Technology', 'CNTT2'),
('ATTT', 'An toàn thông tin', 'Information Security', 'CNTT2'),
('CNĐPT', 'Công nghệ đa phương tiện', 'Multimedia Technology', 'CNTT2'),
('KT', 'Kế toán', 'Accounting', 'CNTT2'),
('MKT', 'Marketing', 'Marketing', 'CNTT2');

# Data Chuyen Nganh
insert into ChuyenNganh (MaChuyenNganh, TenChuyenNganh, MaNganh)
values
('CNCNPM', 'Công nghệ phần mềm', 'CNTT'),
('CNHTTT', 'Hệ thống thông tin', 'CNTT'),
('CNMMTVTT', 'Mạng máy tính và truyền thông', 'CNTT');

INSERT INTO MonHoc (MaMon, TenMon, SoTinChi, SoTietLyThuyet, SoTietBaiTap, SoTietThucHanh, SoTietTuHoc, MaKhoi, TrangThai, NgonNguGiangDay)
VALUES
('THCS1', 'Tin học cơ sở 1', 3, 20, 10, 10, 10, 'KTC', 'HOAT_DONG', 'TIENG_VIET'),
('THCS2', 'Tin học cơ sở 2', 3, 20, 10, 10, 10, 'KTC', 'HOAT_DONG', 'TIENG_VIET'),
('TRR1', 'Toán rời rạc 1', 3, 25, 5, 0, 10, 'KTC', 'HOAT_DONG', 'TIENG_VIET'),
('TRR2', 'Toán rời rạc 2', 3, 25, 5, 0, 10, 'KTC', 'HOAT_DONG', 'TIENG_VIET'),
('HDH', 'Hệ điều hành', 3, 25, 10, 5, 10, 'KTCB', 'HOAT_DONG', 'TIENG_VIET'),
('XLTNS', 'Xử lý tín hiệu số', 3, 25, 10, 5, 10, 'KTCB', 'HOAT_DONG', 'TIENG_VIET'),
('MMT', 'Mạng máy tính', 3, 25, 10, 5, 10, 'KTCB', 'HOAT_DONG', 'TIENG_VIET'),
('PYTHON', 'Lập trình Python', 3, 20, 10, 10, 10, 'KTCS', 'HOAT_DONG', 'TIENG_VIET'),
('TTCS', 'Thực tập cơ sở', 2, 0, 0, 30, 10, 'KTCS', 'HOAT_DONG', 'TIENG_VIET');

INSERT INTO QuanHeMonHoc (MaMonChinh, MaMonLienQuan, LoaiDieuKien)
VALUES
('THCS2', 'THCS1', 'TIEN_QUYET'),
('TRR2', 'TRR1', 'TIEN_QUYET');

# Data Nam Dao Tao
insert into NamDaoTao (Nam)
values
(2019),
(2020),
(2021),
(2022),
(2023),
(2024),
(2025),
(2026),
(2027),
(2028),
(2029),
(2030);

-- CTDT cho ngành Công nghệ phần mềm 2025
INSERT INTO ChuongTrinhDaoTao (MaCTDT, TenCTDT, MoTa, CapBac, MaHe, MaNganh, TrangThai)
VALUES ('CTDT_CNTT_2025', 'Chương trình CNPM 2025', 'CTĐT dành cho CNPM khóa 2025', 'DH', 'CQ', 'CNTT', 'HOAT_DONG');

-- Gán năm vào Nam_CTDT
INSERT INTO Nam_CTDT (Nam, MaCTDT)
VALUES (2025, 'CTDT_CNTT_2025');

INSERT INTO KeHoachHocTap (MaKHHT, MaCTDT, MaChuyenNganh, MoTa)
VALUES ('KHHT_CNPM_2025', 'CTDT_CNTT_2025', 'CNCNPM', 'KHHT cho CNPM khóa 2025');

-- Tạo các kỳ học cho KHHT_CNPM_2025
INSERT INTO KiHoc (MaKi, Ki, MaKHHT)
VALUES
    ('K1_CNPM_2025', 1, 'KHHT_CNPM_2025'),
    ('K2_CNPM_2025', 2, 'KHHT_CNPM_2025'),
    ('K3_CNPM_2025', 3, 'KHHT_CNPM_2025'),
    ('K4_CNPM_2025', 4, 'KHHT_CNPM_2025');

-- Gán môn học cho các kỳ học
-- Kỳ 1
INSERT INTO KiHoc_MonHoc (MaKi, MaMon, LoaiMonHoc)
VALUES
    ('K1_CNPM_2025', 'THCS1', 'BAT_BUOC'),
    ('K1_CNPM_2025', 'TRR1', 'BAT_BUOC'),
    ('K1_CNPM_2025', 'PYTHON', 'BAT_BUOC');

-- Kỳ 2
INSERT INTO KiHoc_MonHoc (MaKi, MaMon, LoaiMonHoc)
VALUES
    ('K2_CNPM_2025', 'THCS2', 'BAT_BUOC'),
    ('K2_CNPM_2025', 'TRR2', 'BAT_BUOC'),
    ('K2_CNPM_2025', 'HDH', 'BAT_BUOC');

-- Kỳ 3
INSERT INTO KiHoc_MonHoc (MaKi, MaMon, LoaiMonHoc)
VALUES
    ('K3_CNPM_2025', 'MMT', 'BAT_BUOC'),
    ('K3_CNPM_2025', 'XLTNS', 'BAT_BUOC');

-- Kỳ 4
INSERT INTO KiHoc_MonHoc (MaKi, MaMon, LoaiMonHoc)
VALUES
    ('K4_CNPM_2025', 'TTCS', 'BAT_BUOC');

