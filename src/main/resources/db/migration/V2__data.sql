insert into Khoa (MaKhoa, TenKhoa) values ('CNTT', 'Khoa Công nghệ thông tin');

insert into VaiTro (MaVaiTro, TenVaiTro)
values ('EMPLOYEE', 'ROLE_EMPLOYEE'),
       ('ADMIN', 'ROLE_ADMIN');


insert into TaiKhoan (MaTaiKhoan, TenDangNhap,MatKhau, MaVaiTro)
values ('admin', 'admin', 'admin123', 'ADMIN'),
       ('employee', 'employee', 'employee123', 'EMPLOYEE');


insert into NhanVien (MaNhanVien, HoTen, NgayThangNamSinh, SoDienThoai, Email, GioiTinh, MaTaiKhoan)
values ('123', 'Nguyen Van A', '2000/01/20', '123456789', 'employee@gmail.com', 'MALE','employee')
