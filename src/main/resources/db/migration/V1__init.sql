-- ==========================================
-- TABLE: BacDaoTao (Bậc đào tạo)
-- ==========================================
create table BacDaoTao (
       MaBac          varchar(21)              not null primary key,
       TenCapBac      varchar(100)             null,
       ThoiGianDaoTao float                    null,
       NgayTao        date default (curdate()) null
);

-- ==========================================
-- TABLE: HeDaoTao (Hệ đào tạo)
-- ==========================================
create table HeDaoTao (
      MaHe  varchar(21)  not null primary key,
      TenHe varchar(100) null
);

-- ==========================================
-- TABLE: Khoa (Khoa đào tạo)
-- ==========================================
create table Khoa (
  MaKhoa  varchar(21)              not null primary key,
  TenKhoa varchar(100)             null,
  NgayTao date default (curdate()) null
);

-- ==========================================
-- TABLE: KhoiKienThuc (Khối kiến thức)
-- ==========================================
create table KhoiKienThuc (
          MaKhoi  varchar(21)  not null primary key,
          TenKhoi varchar(100) null,
          MoTa    text         null,
          Parent  varchar(21)  null,
          constraint fk_parent foreign key (Parent) references KhoiKienThuc (MaKhoi)
);

-- ==========================================
-- TABLE: MonHoc (Môn học)
-- ==========================================
create table MonHoc (
    MaMon           varchar(21)                                               not null primary key,
    TenMon          varchar(100)                                              null,
    SoTinChi        int                                                       null,
    SoTietLyThuyet  int                                                       null,
    SoTietBaiTap    int                                                       null,
    SoTietThucHanh  int                                                       null,
    SoTietTuHoc     int                                                       null,
    MaKhoi          varchar(21)                                               null,
    TrangThai       enum('HOAT_DONG', 'NGUNG_HOAT_DONG') default 'HOAT_DONG' not null,
    NgonNguGiangDay enum('TIENG_VIET', 'TIENG_ANH')                           not null,
    constraint MonHoc_ibfk_1 foreign key (MaKhoi) references KhoiKienThuc (MaKhoi)
);

create index MaKhoi on MonHoc (MaKhoi);

-- ==========================================
-- TABLE: NamDaoTao (Năm đào tạo)
-- ==========================================
create table NamDaoTao (
       Nam int not null primary key
);

-- ==========================================
-- TABLE: NganhDaoTao (Ngành đào tạo)
-- ==========================================
create table NganhDaoTao (
         MaNganh    varchar(21)              not null primary key,
         TenNganhTV varchar(100)             null,
         TenNganhTA varchar(100)             null,
         MaKhoa     varchar(21)              null,
         NgayTao    date default (curdate()) null,
         constraint NganhDaoTao_ibfk_1 foreign key (MaKhoa) references Khoa (MaKhoa)
);

create index MaKhoa on NganhDaoTao (MaKhoa);

-- ==========================================
-- TABLE: ChuongTrinhDaoTao (Chương trình đào tạo)
-- ==========================================
create table ChuongTrinhDaoTao (
               MaCTDT    varchar(21)                                               not null primary key,
               TenCTDT   varchar(100)                                              null,
               MoTa      text                                                      null,
               CapBac    varchar(21)                                               null,
               MaHe      varchar(21)                                               null,
               MaNganh   varchar(21)                                               null,
               TrangThai enum('HOAT_DONG', 'NGUNG_HOAT_DONG') default 'HOAT_DONG' not null,
               constraint ChuongTrinhDaoTao_ibfk_1 foreign key (CapBac) references BacDaoTao (MaBac),
               constraint ChuongTrinhDaoTao_ibfk_2 foreign key (MaHe) references HeDaoTao (MaHe),
               constraint ChuongTrinhDaoTao_ibfk_3 foreign key (MaNganh) references NganhDaoTao (MaNganh)
);

create index CapBac on ChuongTrinhDaoTao (CapBac);
create index MaHe on ChuongTrinhDaoTao (MaHe);
create index MaNganh on ChuongTrinhDaoTao (MaNganh);

-- ==========================================
-- TABLE: ChuyenNganh (Chuyên ngành)
-- ==========================================
create table ChuyenNganh (
         MaChuyenNganh  varchar(21)              not null primary key,
         TenChuyenNganh varchar(100)             null,
         MaNganh        varchar(21)              null,
         NgayTao        date default (curdate()) null,
         constraint ChuyenNganh_ibfk_1 foreign key (MaNganh) references NganhDaoTao (MaNganh)
);

create index MaNganh on ChuyenNganh (MaNganh);

-- ==========================================
-- TABLE: KeHoachHocTap (Kế hoạch học tập)
-- ==========================================
create table KeHoachHocTap (
       MaKHHT        varchar(21) not null primary key,
       MaCTDT        varchar(21) null,
       MaChuyenNganh varchar(21) null,
       MoTa          text        null,
       constraint KeHoachHocTap_ibfk_1 foreign key (MaCTDT) references ChuongTrinhDaoTao (MaCTDT),
       constraint KeHoachHocTap_ibfk_2 foreign key (MaChuyenNganh) references ChuyenNganh (MaChuyenNganh)
);

create index MaCTDT on KeHoachHocTap (MaCTDT);
create index MaChuyenNganh on KeHoachHocTap (MaChuyenNganh);

-- ==========================================
-- TABLE: KiHoc (Kỳ học)
-- ==========================================
create table KiHoc (
   MaKi   varchar(21) not null primary key,
   Ki     int         null,
   MaKHHT varchar(21) null,
   constraint KiHoc_ibfk_1 foreign key (MaKHHT) references KeHoachHocTap (MaKHHT)
);

create index MaKHHT on KiHoc (MaKHHT);

-- ==========================================
-- TABLE: KiHoc_MonHoc (Liên kết kỳ học - môn học)
-- ==========================================
create table KiHoc_MonHoc (
          MaKi       varchar(21)                                         not null,
          MaMon      varchar(21)                                         not null,
          LoaiMonHoc enum('BAT_BUOC', 'TU_CHON', 'THAY_THE_TOT_NGHIEP') null,
          primary key (MaKi, MaMon),
          constraint KiHoc_MonHoc_ibfk_1 foreign key (MaKi) references KiHoc (MaKi),
          constraint KiHoc_MonHoc_ibfk_2 foreign key (MaMon) references MonHoc (MaMon)
);

create index MaMon on KiHoc_MonHoc (MaMon);

-- ==========================================
-- TABLE: Nam_CTDT (Liên kết năm - chương trình đào tạo)
-- ==========================================
create table Nam_CTDT (
      Nam    int         not null,
      MaCTDT varchar(21) not null,
      primary key (Nam, MaCTDT),
      constraint Nam_CTDT_ibfk_1 foreign key (Nam) references NamDaoTao (Nam),
      constraint Nam_CTDT_ibfk_2 foreign key (MaCTDT) references ChuongTrinhDaoTao (MaCTDT)
);

create index MaCTDT on Nam_CTDT (MaCTDT);

-- ==========================================
-- TABLE: QuanHeMonHoc (Quan hệ giữa các môn học)
-- ==========================================
create table QuanHeMonHoc (
          MaMonChinh    varchar(21)                                   not null,
          MaMonLienQuan varchar(21)                                   not null,
          LoaiDieuKien  enum('TIEN_QUYET', 'SONG_HANH', 'HOC_TRUOC') null,
          primary key (MaMonChinh, MaMonLienQuan),
          constraint QuanHeMonHoc_ibfk_1 foreign key (MaMonChinh) references MonHoc (MaMon),
          constraint QuanHeMonHoc_ibfk_2 foreign key (MaMonLienQuan) references MonHoc (MaMon)
);

create index MaMonLienQuan on QuanHeMonHoc (MaMonLienQuan);

-- ==========================================
-- TABLE: VaiTro (Vai trò người dùng)
-- ==========================================
create table VaiTro (
    MaVaiTro  varchar(21)  not null primary key,
    TenVaiTro varchar(100) null,
    MoTa      text         null
);

-- ==========================================
-- TABLE: TaiKhoan (Tài khoản hệ thống)
-- ==========================================
create table TaiKhoan (
      MaTaiKhoan  varchar(21)                                               not null primary key,
      TenDangNhap varchar(50)                                               null,
      MatKhau     varchar(255)                                              null,
      MaVaiTro    varchar(21)                                               null,
      TrangThai   enum('HOAT_DONG', 'NGUNG_HOAT_DONG') default 'HOAT_DONG' not null,
      constraint TenDangNhap unique (TenDangNhap),
      constraint TaiKhoan_ibfk_1 foreign key (MaVaiTro) references VaiTro (MaVaiTro)
);

create index MaVaiTro on TaiKhoan (MaVaiTro);

-- ==========================================
-- TABLE: NhanVien (Thông tin nhân viên)
-- ==========================================
create table NhanVien (
      MaNhanVien       varchar(21)        not null primary key,
      Email            varchar(100)       null,
      SoDienThoai      varchar(15)        null,
      HoTen            varchar(100)       null,
      NgayThangNamSinh date               null,
      MaTaiKhoan       varchar(21)        null,
      GioiTinh         enum('NAM', 'NU')  not null,
      constraint NhanVien_ibfk_1 foreign key (MaTaiKhoan) references TaiKhoan (MaTaiKhoan)
);

create index MaTaiKhoan on NhanVien (MaTaiKhoan);
