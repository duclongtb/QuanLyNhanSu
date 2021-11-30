CREATE DATABASE QuanLyNhanSuHaui
USE QuanLyNhanSuHaui

/****** Object:  Table [dbo].[BacLuong]    Script Date: 5/11/2021 10:10:02 PM ******/

CREATE TABLE BacLuong(
	BacLuong int NOT NULL,
	HeSoLuong float NULL,
PRIMARY KEY(BacLuong ASC)
)
GO
/****** Object:  Table [dbo].[ChucVu]    Script Date: 5/11/2021 10:10:02 PM ******/

CREATE TABLE ChucVu(
	MaCV nvarchar(10) NOT NULL,
	TenCV nvarchar(50) NULL,
	PhuCap float NULL,
	BacLuong int NULL,
PRIMARY KEY(MaCV ASC),
FOREIGN KEY (BacLuong) REFERENCES BacLuong(BacLuong)
)
GO
/****** Object:  Table [dbo].[NhanSu]    Script Date: 5/11/2021 10:10:02 PM ******/

CREATE TABLE NhanSu(
	MaNS varchar(10) NOT NULL,
	HoTen nvarchar(50) NULL,
	NgaySinh date NULL,
	QueQuan nvarchar(100) NULL,
	GioiTinh bit NULL,
	DanToc nvarchar(20) NULL,
	SoDienThoai varchar(12) NULL,
	TrinhDoHocVan nvarchar(50) NULL,
	ChuyenNganh nvarchar(50) NULL,
	MaPB varchar(10) NULL,
	MaCV nvarchar(10) NULL,
	ChinhTri nvarchar(250) NULL,
	DoanThe nvarchar(250) NULL,
	Anh blob NULL,
	NgayGiaNhap date NULL,
	SuaCuoi nvarchar(50) NULL,
	CanCuoc varchar(20) NULL,
	LoaiCongChuc bit NULL,
	HanHopDong date NULL,
 CONSTRAINT PK__NhanSu__2725D73784D2F873 PRIMARY KEY(MaNS ASC),
 FOREIGN KEY (MaCV) REFERENCES ChucVu(MaCV)
)
GO
/****** Object:  Table [dbo].[PhongBan]    Script Date: 5/11/2021 10:10:02 PM ******/

CREATE TABLE PhongBan(
	MaPB varchar(10) NOT NULL,
	TenPB nvarchar(50) NULL,
	SoDienThoaiPB varchar(12) NULL,
PRIMARY KEY(MaPB ASC)
)
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 5/11/2021 10:10:02 PM ******/

CREATE TABLE TaiKhoan(
	TenTaiKhoan varchar(30) NOT NULL,
	MatKhau varchar(30) NOT NULL,
	Ten nvarchar(50) NULL,
	Anh blob NULL,
PRIMARY KEY(TenTaiKhoan ASC)
) 

