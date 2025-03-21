-- 1. VaiTro
CREATE TABLE VaiTro (
                        MaVaiTro VARCHAR(21) PRIMARY KEY,
                        TenVaiTro VARCHAR(100),
                        MoTa TEXT
);

-- 2. Quyen
CREATE TABLE Quyen (
                       MaQuyen VARCHAR(21) PRIMARY KEY,
                       TenQuyen VARCHAR(100)
);

-- 3. Quyen_VaiTro
CREATE TABLE Quyen_VaiTro (
                              MaVaiTro VARCHAR(21),
                              MaQuyen VARCHAR(21),
                              PRIMARY KEY (MaVaiTro, MaQuyen),
                              FOREIGN KEY (MaVaiTro) REFERENCES VaiTro(MaVaiTro),
                              FOREIGN KEY (MaQuyen) REFERENCES Quyen(MaQuyen)
);

-- 4. TaiKhoan
CREATE TABLE TaiKhoan (
                          MaTaiKhoan VARCHAR(21) PRIMARY KEY,
                          TenDangNhap VARCHAR(50) UNIQUE,
                          MatKhau VARCHAR(255),
                          MaVaiTro VARCHAR(21),
                          FOREIGN KEY (MaVaiTro) REFERENCES VaiTro(MaVaiTro)
);

-- 5. QuanTriVien
CREATE TABLE QuanTriVien (
                             MaQTV VARCHAR(21) PRIMARY KEY,
                             Email VARCHAR(100),
                             SoDienThoai VARCHAR(15),
                             HoTen VARCHAR(100),
                             NgayThangNamSinh DATE,
                             GioiTinh VARCHAR(10),
                             MaTaiKhoan VARCHAR(21),
                             FOREIGN KEY (MaTaiKhoan) REFERENCES TaiKhoan(MaTaiKhoan)
);

-- 6. NhanVien
CREATE TABLE NhanVien (
                          MaNhanVien VARCHAR(21) PRIMARY KEY,
                          Email VARCHAR(100),
                          SoDienThoai VARCHAR(15),
                          HoTen VARCHAR(100),
                          NgayThangNamSinh DATE,
                          GioiTinh VARCHAR(10),
                          MaTaiKhoan VARCHAR(21),
                          FOREIGN KEY (MaTaiKhoan) REFERENCES TaiKhoan(MaTaiKhoan)
);

-- 7. Khoa
CREATE TABLE Khoa (
                      MaKhoa VARCHAR(21) PRIMARY KEY,
                      TenKhoa VARCHAR(100)
);

-- 8. NganhDaoTao
CREATE TABLE NganhDaoTao (
                             MaNganh VARCHAR(21) PRIMARY KEY,
                             TenNganhTV VARCHAR(100),
                             TenNganhTA VARCHAR(100),
                             MaKhoa VARCHAR(21),
                             FOREIGN KEY (MaKhoa) REFERENCES Khoa(MaKhoa)
);

-- 9. ChuyenNganh
CREATE TABLE ChuyenNganh (
                             MaChuyenNganh VARCHAR(21) PRIMARY KEY,
                             TenChuyenNganh VARCHAR(100),
                             MaNganh VARCHAR(21),
                             FOREIGN KEY (MaNganh) REFERENCES NganhDaoTao(MaNganh)
);

-- 10. HeDaoTao
CREATE TABLE HeDaoTao (
                          MaHe VARCHAR(21) PRIMARY KEY,
                          TenHe VARCHAR(100)
);

-- 11. BacDaoTao
CREATE TABLE BacDaoTao (
                           MaBac VARCHAR(21) PRIMARY KEY,
                           TenCapBac VARCHAR(100),
                           ThoiGianDaoTao FLOAT
);

-- 12. ChuongTrinhDaoTao
CREATE TABLE ChuongTrinhDaoTao (
                                   MaCTDT VARCHAR(21) PRIMARY KEY,
                                   TenCTDT VARCHAR(100),
                                   MoTa TEXT,
                                   TrangThai VARCHAR(21),
                                   CapBac VARCHAR(21),
                                   MaHe VARCHAR(21),
                                   MaNganh VARCHAR(21),
                                   FOREIGN KEY (CapBac) REFERENCES BacDaoTao(MaBac),
                                   FOREIGN KEY (MaHe) REFERENCES HeDaoTao(MaHe),
                                   FOREIGN KEY (MaNganh) REFERENCES NganhDaoTao(MaNganh)
);

-- 13. NamDaoTao
CREATE TABLE NamDaoTao (
                           Nam INT PRIMARY KEY
);

-- 14. Nam_CTDT
CREATE TABLE Nam_CTDT (
                          Nam INT,
                          MaCTDT VARCHAR(21),
                          PRIMARY KEY (Nam, MaCTDT),
                          FOREIGN KEY (Nam) REFERENCES NamDaoTao(Nam),
                          FOREIGN KEY (MaCTDT) REFERENCES ChuongTrinhDaoTao(MaCTDT)
);

-- 15. KeHoachHocTap
CREATE TABLE KeHoachHocTap (
                               MaKHHT VARCHAR(21) PRIMARY KEY,
                               MaCTDT VARCHAR(21),
                               MaChuyenNganh VARCHAR(21),
                               MoTa TEXT,
                               FOREIGN KEY (MaCTDT) REFERENCES ChuongTrinhDaoTao(MaCTDT),
                               FOREIGN KEY (MaChuyenNganh) REFERENCES ChuyenNganh(MaChuyenNganh)
);

-- 16. KhoiKienThuc
CREATE TABLE KhoiKienThuc (
                              MaKhoi VARCHAR(21) PRIMARY KEY,
                              TenKhoi VARCHAR(100),
                              MoTa TEXT
);

-- 17. MonHoc
CREATE TABLE MonHoc (
                        MaMon VARCHAR(21) PRIMARY KEY,
                        TenMon VARCHAR(100),
                        SoTinChi INT,
                        SoTietLyThuyet INT,
                        SoTietBaiTap INT,
                        SoTietThucHanh INT,
                        SoTietTuHoc INT,
                        NgonNguGiangDay VARCHAR(21),
                        TrangThai VARCHAR(21),
                        MaKhoi VARCHAR(21),
                        FOREIGN KEY (MaKhoi) REFERENCES KhoiKienThuc(MaKhoi)
);

-- 18. QuanHeMonHoc
CREATE TABLE QuanHeMonHoc (
                              MaMonChinh VARCHAR(21),
                              MaMonLienQuan VARCHAR(21),
                              LoaiDieuKien VARCHAR(21),
                              PRIMARY KEY (MaMonChinh, MaMonLienQuan),
                              FOREIGN KEY (MaMonChinh) REFERENCES MonHoc(MaMon),
                              FOREIGN KEY (MaMonLienQuan) REFERENCES MonHoc(MaMon)
);

-- 19. LoaiMonHoc
CREATE TABLE LoaiMonHoc (
                            MaLoaiMH VARCHAR(21) PRIMARY KEY,
                            TenLoai VARCHAR(100)
);

-- 20. KiHoc
CREATE TABLE KiHoc (
                       MaKi VARCHAR(21) PRIMARY KEY,
                       Ki INT,
                       MaKHHT VARCHAR(21),
                       FOREIGN KEY (MaKHHT) REFERENCES KeHoachHocTap(MaKHHT)
);

-- 21. KiHoc_MonHoc
CREATE TABLE KiHoc_MonHoc (
                              MaKi VARCHAR(21),
                              MaMon VARCHAR(21),
                              MaLoaiMH VARCHAR(21),
                              PRIMARY KEY (MaKi, MaMon),
                              FOREIGN KEY (MaKi) REFERENCES KiHoc(MaKi),
                              FOREIGN KEY (MaMon) REFERENCES MonHoc(MaMon),
                              FOREIGN KEY (MaLoaiMH) REFERENCES LoaiMonHoc(MaLoaiMH)
);