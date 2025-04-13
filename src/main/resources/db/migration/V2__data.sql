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
# password: 123

insert into NhanVien (MaNhanVien, HoTen, NgayThangNamSinh, SoDienThoai, Email, GioiTinh, MaTaiKhoan)
values
('123', 'Nguyen Van A', '2000/01/20', '12345678910', 'employee@gmail.com', 'NAM','employee');


# Data Bac Dao Tao
insert into BacDaoTao (MaBac, TenCapBac, ThoiGianDaoTao)
values
('DH', 'Đại học', 4.5),
('TH', 'Thạc sĩ', 2),
('TS', 'Tiến sĩ', 4);

# Data He Dao Tao
insert into HeDaoTao (MaHe, TenHe)
values
('CHINHQUY', 'Chính quy'),
('VHVL', 'Vừa làm vừa học'),
('TUXA', 'Đào tạo từ xa');

# Data Nganh Dao Tao
insert into NganhDaoTao (MaNganh, TenNganhTA, TenNganhTV, MaKhoa)
values
('CNTT', 'Information Technology', 'Công nghệ thông tin', 'CNTT2');

# Data Chuyen Nganh
insert into ChuyenNganh (MaChuyenNganh, TenChuyenNganh, MaNganh)
values
('CNCNPM', 'Công nghệ phần mềm', 'CNTT'),
('CNHTTT', 'Hệ thống thông tin', 'CNTT'),
('CNMMTVTT', 'Mạng máy tính và truyền thông', 'CNTT');

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