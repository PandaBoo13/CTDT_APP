create table BacDaoTao
(
    MaBac          varchar(21)              not null
        primary key,
    TenCapBac      varchar(100)             null,
    ThoiGianDaoTao float                    null,
    NgayTao        date default (curdate()) null
);

create table HeDaoTao
(
    MaHe  varchar(21)  not null
        primary key,
    TenHe varchar(100) null
);

create table Khoa
(
    MaKhoa  varchar(21)              not null
        primary key,
    TenKhoa varchar(100)             null,
    NgayTao date default (curdate()) null
);

create table KhoiKienThuc
(
    MaKhoi  varchar(21)  not null
        primary key,
    TenKhoi varchar(100) null,
    MoTa    text         null
);

create table LoaiMonHoc
(
    MaLoaiMH varchar(21)  not null
        primary key,
    TenLoai  varchar(100) null
);

create table MonHoc
(
    MaMon           varchar(21)  not null
        primary key,
    TenMon          varchar(100) null,
    SoTinChi        int          null,
    SoTietLyThuyet  int          null,
    SoTietBaiTap    int          null,
    SoTietThucHanh  int          null,
    SoTietTuHoc     int          null,
    NgonNguGiangDay varchar(21)  null,
    TrangThai       varchar(21)  null,
    MaKhoi          varchar(21)  null,
    constraint MonHoc_ibfk_1
        foreign key (MaKhoi) references KhoiKienThuc (MaKhoi)
);

create index MaKhoi
    on MonHoc (MaKhoi);

create table NamDaoTao
(
    Nam int not null
        primary key
);

create table NganhDaoTao
(
    MaNganh    varchar(21)              not null
        primary key,
    TenNganhTV varchar(100)             null,
    TenNganhTA varchar(100)             null,
    MaKhoa     varchar(21)              null,
    NgayTao    date default (curdate()) null,
    constraint NganhDaoTao_ibfk_1
        foreign key (MaKhoa) references Khoa (MaKhoa)
);

create table ChuongTrinhDaoTao
(
    MaCTDT    varchar(21)  not null
        primary key,
    TenCTDT   varchar(100) null,
    MoTa      text         null,
    TrangThai varchar(21)  null,
    CapBac    varchar(21)  null,
    MaHe      varchar(21)  null,
    MaNganh   varchar(21)  null,
    constraint ChuongTrinhDaoTao_ibfk_1
        foreign key (CapBac) references BacDaoTao (MaBac),
    constraint ChuongTrinhDaoTao_ibfk_2
        foreign key (MaHe) references HeDaoTao (MaHe),
    constraint ChuongTrinhDaoTao_ibfk_3
        foreign key (MaNganh) references NganhDaoTao (MaNganh)
);

create index CapBac
    on ChuongTrinhDaoTao (CapBac);

create index MaHe
    on ChuongTrinhDaoTao (MaHe);

create index MaNganh
    on ChuongTrinhDaoTao (MaNganh);

create table ChuyenNganh
(
    MaChuyenNganh  varchar(21)              not null
        primary key,
    TenChuyenNganh varchar(100)             null,
    MaNganh        varchar(21)              null,
    NgayTao        date default (curdate()) null,
    constraint ChuyenNganh_ibfk_1
        foreign key (MaNganh) references NganhDaoTao (MaNganh)
);

create index MaNganh
    on ChuyenNganh (MaNganh);

create table KeHoachHocTap
(
    MaKHHT        varchar(21) not null
        primary key,
    MaCTDT        varchar(21) null,
    MaChuyenNganh varchar(21) null,
    MoTa          text        null,
    constraint KeHoachHocTap_ibfk_1
        foreign key (MaCTDT) references ChuongTrinhDaoTao (MaCTDT),
    constraint KeHoachHocTap_ibfk_2
        foreign key (MaChuyenNganh) references ChuyenNganh (MaChuyenNganh)
);

create index MaCTDT
    on KeHoachHocTap (MaCTDT);

create index MaChuyenNganh
    on KeHoachHocTap (MaChuyenNganh);

create table KiHoc
(
    MaKi   varchar(21) not null
        primary key,
    Ki     int         null,
    MaKHHT varchar(21) null,
    constraint KiHoc_ibfk_1
        foreign key (MaKHHT) references KeHoachHocTap (MaKHHT)
);

create index MaKHHT
    on KiHoc (MaKHHT);

create table KiHoc_MonHoc
(
    MaKi     varchar(21) not null,
    MaMon    varchar(21) not null,
    MaLoaiMH varchar(21) null,
    primary key (MaKi, MaMon),
    constraint KiHoc_MonHoc_ibfk_1
        foreign key (MaKi) references KiHoc (MaKi),
    constraint KiHoc_MonHoc_ibfk_2
        foreign key (MaMon) references MonHoc (MaMon),
    constraint KiHoc_MonHoc_ibfk_3
        foreign key (MaLoaiMH) references LoaiMonHoc (MaLoaiMH)
);

create index MaLoaiMH
    on KiHoc_MonHoc (MaLoaiMH);

create index MaMon
    on KiHoc_MonHoc (MaMon);

create table Nam_CTDT
(
    Nam    int         not null,
    MaCTDT varchar(21) not null,
    primary key (Nam, MaCTDT),
    constraint Nam_CTDT_ibfk_1
        foreign key (Nam) references NamDaoTao (Nam),
    constraint Nam_CTDT_ibfk_2
        foreign key (MaCTDT) references ChuongTrinhDaoTao (MaCTDT)
);

create index MaCTDT
    on Nam_CTDT (MaCTDT);

create index MaKhoa
    on NganhDaoTao (MaKhoa);

create table QuanHeMonHoc
(
    MaMonChinh    varchar(21) not null,
    MaMonLienQuan varchar(21) not null,
    LoaiDieuKien  varchar(21) null,
    primary key (MaMonChinh, MaMonLienQuan),
    constraint QuanHeMonHoc_ibfk_1
        foreign key (MaMonChinh) references MonHoc (MaMon),
    constraint QuanHeMonHoc_ibfk_2
        foreign key (MaMonLienQuan) references MonHoc (MaMon)
);

create index MaMonLienQuan
    on QuanHeMonHoc (MaMonLienQuan);

create table Quyen
(
    MaQuyen  varchar(21)  not null
        primary key,
    TenQuyen varchar(100) null
);

create table VaiTro
(
    MaVaiTro  varchar(21)  not null
        primary key,
    TenVaiTro varchar(100) null,
    MoTa      text         null
);

create table Quyen_VaiTro
(
    MaVaiTro varchar(21) not null,
    MaQuyen  varchar(21) not null,
    primary key (MaVaiTro, MaQuyen),
    constraint Quyen_VaiTro_ibfk_1
        foreign key (MaVaiTro) references VaiTro (MaVaiTro),
    constraint Quyen_VaiTro_ibfk_2
        foreign key (MaQuyen) references Quyen (MaQuyen)
);

create index MaQuyen
    on Quyen_VaiTro (MaQuyen);

create table TaiKhoan
(
    MaTaiKhoan  varchar(21)  not null
        primary key,
    TenDangNhap varchar(50)  null,
    MatKhau     varchar(255) null,
    MaVaiTro    varchar(21)  null,
    constraint TenDangNhap
        unique (TenDangNhap),
    constraint TaiKhoan_ibfk_1
        foreign key (MaVaiTro) references VaiTro (MaVaiTro)
);

create table NhanVien
(
    MaNhanVien       varchar(21)  not null
        primary key,
    Email            varchar(100) null,
    SoDienThoai      varchar(15)  null,
    HoTen            varchar(100) null,
    NgayThangNamSinh date         null,
    GioiTinh         varchar(10)  null,
    MaTaiKhoan       varchar(21)  null,
    constraint NhanVien_ibfk_1
        foreign key (MaTaiKhoan) references TaiKhoan (MaTaiKhoan)
);

create index MaTaiKhoan
    on NhanVien (MaTaiKhoan);

create table QuanTriVien
(
    MaQTV            varchar(21)  not null
        primary key,
    Email            varchar(100) null,
    SoDienThoai      varchar(15)  null,
    HoTen            varchar(100) null,
    NgayThangNamSinh date         null,
    GioiTinh         varchar(10)  null,
    MaTaiKhoan       varchar(21)  null,
    constraint QuanTriVien_ibfk_1
        foreign key (MaTaiKhoan) references TaiKhoan (MaTaiKhoan)
);

create index MaTaiKhoan
    on QuanTriVien (MaTaiKhoan);

create index MaVaiTro
    on TaiKhoan (MaVaiTro);
