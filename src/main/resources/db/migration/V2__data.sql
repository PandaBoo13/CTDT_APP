-- Data Khoa
insert into Khoa (MaKhoa, TenKhoa)
values
('CNTT2', 'Khoa Công nghệ thông tin 2');

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
('HPTC', 'Học phần tự chọn', 'Mô tả ...', 'KTCN'),
('HPTTTN', 'Học phần thay thế tốt nghiệp', 'Mô tả ...', 'KTCN');

insert into VaiTro (MaVaiTro, TenVaiTro)
values
('EMPLOYEE', 'ROLE_EMPLOYEE'),
('ADMIN', 'ROLE_ADMIN');

# password: 123
insert into TaiKhoan (MaTaiKhoan, TenDangNhap,MatKhau, MaVaiTro)
values
('admin', 'admin', '$2a$10$Eof5DH8meJQDpnVp2GgA9.qjdHiP0IiAUr0SEZwozU8oWgpfjbiZ.', 'ADMIN'),
('employee', 'employee', '$2a$10$Eof5DH8meJQDpnVp2GgA9.qjdHiP0IiAUr0SEZwozU8oWgpfjbiZ.', 'EMPLOYEE');

insert into NhanVien (MaNhanVien, HoTen, NgayThangNamSinh, SoDienThoai, Email, GioiTinh, MaTaiKhoan)
values
('123', 'Nguyen Van A', '2000/01/20', '12345678910', 'employee@gmail.com', 'NAM','employee')
