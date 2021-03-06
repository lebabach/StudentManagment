CREATE DATABASE QuanLyHocSinh
GO --DROP DATABASE QuanLyHocSinh
USE QuanLyHocSinh
GO
CREATE TABLE Khoi(
	KhoiID int  PRIMARY KEY IDENTITY(1,1),
	TenKhoi nvarchar(50)--khối 6
)
Go
INSERT INTO Khoi
VALUES (N'Khối lớp 6')
INSERT INTO Khoi
VALUES (N'Khối lớp 7') 
INSERT INTO Khoi
VALUES (N'Khối lớp 8') 
INSERT INTO Khoi
VALUES (N'Khối lớp 9')  
GO
CREATE TABLE Lop(
	LopID int PRIMARY KEY IDENTITY(1,1),
	KhoiID int,
	Tenlop nvarchar(20),
	siso int DEFAULT(0),
	NamHoc NVARCHAR(10),
	FOREIGN KEY (KhoiID) REFERENCES Khoi(KhoiID) ON UPDATE CASCADE ON DELETE CASCADE
)
GO
INSERT INTO Lop(KhoiID,Tenlop,NamHoc)
VALUES (1,N'6A','2011')
INSERT INTO Lop(KhoiID,Tenlop,NamHoc)
VALUES (1,N'6B','2011') 
INSERT INTO Lop(KhoiID,Tenlop,NamHoc)
VALUES (2,N'7A','2010') 
INSERT INTO Lop(KhoiID,Tenlop,NamHoc)
VALUES (2,N'7B','2010') 
 
INSERT INTO Lop(KhoiID,Tenlop,NamHoc)
VALUES (3,N'8A','2009')
INSERT INTO Lop(KhoiID,Tenlop,NamHoc)
VALUES (3,N'8B','2009') 
INSERT INTO Lop(KhoiID,Tenlop,NamHoc)
VALUES (4,N'9A','2008') 
INSERT INTO Lop(KhoiID,Tenlop,NamHoc)
VALUES (4,N'9B','2008')  
--select * from lop
GO
CREATE TABLE Monhoc(
	MonhocID INT PRIMARY KEY IDENTITY(1,1),
	TenMH nvarchar(30),
	HeSo int,
	KhoiID INT,
	FOREIGN KEY (KhoiID) REFERENCES Khoi(KhoiID) ON UPDATE CASCADE ON DELETE CASCADE
)
GO --Select * from khoi
INSERT INTO Monhoc
VALUES (N'Toán Lớp 6',2,1)
INSERT INTO Monhoc
VALUES (N'Văn Lớp 6',2,1)

INSERT INTO Monhoc
VALUES (N'Thể Dục Lớp 6',1,1)
INSERT INTO Monhoc
VALUES (N'Địa Lý Lớp 6',1,1)
INSERT INTO Monhoc
VALUES (N'Lịch Sử Lớp 6',1,1)
INSERT INTO Monhoc
VALUES (N'Giáo Dục Công Dân Lớp 6',1,1)
--SELECT * from Monhoc
GO
CREATE TABLE GiaoVien
(
	GiaoVienID int PRIMARY KEY IDENTITY(1,1),
	MonhocID int,
	TenGiaoVien NVARCHAR(50),
	Gioitinh nvarchar(5),
	Diachi nvarchar(50),
	SoDienThoai NVARCHAR(20),
	Avatar nvarchar(200) DEFAULT(N'/Images/Avatar/NoAvatarSV.jpg'),
	FOREIGN KEY (MonhocID) REFERENCES Monhoc(MonhocID) ON UPDATE CASCADE ON DELETE CASCADE
)
GO 
--select * from lop
--select * from Monhoc
INSERT INTO GiaoVien (MonhocID,TenGiaoVien,Gioitinh,Diachi,SoDienThoai)
VALUES (1,N'Lê Bá Bách',N'Nam',N'17/39 trần phú,Huế','0543848590')
INSERT INTO GiaoVien (MonhocID,TenGiaoVien,Gioitinh,Diachi,SoDienThoai)
VALUES (2,N'Phạm Hồ Trọng Nguyên',N'Nam',N'17/39 trần phú,Huế','0543848590')
INSERT INTO GiaoVien (MonhocID,TenGiaoVien,Gioitinh,Diachi,SoDienThoai)
VALUES (3,N'Nguyễn Tuấn Anh',N'Nam',N'17 trần cao,Huế','0543833590')
INSERT INTO GiaoVien (MonhocID,TenGiaoVien,Gioitinh,Diachi,SoDienThoai)
VALUES (4,N'Đoàn Minh Tuấn',N'Nam',N'139 Hoàng Diệu,Huế','0543448590')
INSERT INTO GiaoVien (MonhocID,TenGiaoVien,Gioitinh,Diachi,SoDienThoai)
VALUES (5,N'Nguyễn Anh Tuấn',N'Nam',N'39 Trần Quốc Toản,Huế','0543848555')
INSERT INTO GiaoVien (MonhocID,TenGiaoVien,Gioitinh,Diachi,SoDienThoai)
VALUES (6,N'Nguyễn Viết Quốc',N'Nam',N'9 trần phú,Huế','0543844555')
--SELECT * from GiaoVien
GO
CREATE TABLE HocSinh(
	HocSinhID int PRIMARY KEY IDENTITY(1,1),
	LopID int,
	Ten nvarchar(50),
	GioiTinh nvarchar(5),
	Diachi nvarchar(50),
	SoDienThoai NVARCHAR(20),
	Avatar nvarchar(200) DEFAULT(N'/Images/Sinhvien/NoAvatarSV.jpg'),
	FOREIGN KEY (LopID) REFERENCES Lop(LopID) ON UPDATE CASCADE ON DELETE CASCADE
)
GO
-- select * from lop

CREATE PROC InsertHocSinh
@LopID int,
@Ten nvarchar(50),
@GioiTinh nvarchar(5),
@Diachi nvarchar(50),
@SoDienThoai NVARCHAR(20)
AS
BEGIN
	INSERT INTO HocSinh (LopID,Ten,GioiTinh,Diachi,SoDienThoai)
	VALUES (@LopID,@Ten,@GioiTinh,@Diachi,@SoDienThoai)
	UPDATE Lop SET siso=siso+1 WHERE LopID=@LopID
END
GO
--SELECT * FROM HocSinh
-- học sinh ở lớp 6A
EXEC InsertHocSinh 1,N'Nguyễn Xuân Trường',N'Nam',N'3 Hoàng Diệu,Huế','0543848590'
EXEC InsertHocSinh 1,N'Phan Gia Long',N'Nam',N'39 Bạch Đằng,Huế','0543111590'
EXEC InsertHocSinh 1,N'Nguyễn Phương Nam',N'Nam',N'39 Lê Lợi,Huế','0542228590'
EXEC InsertHocSinh 1,N'Lê Bá Bá',N'Nam',N'39 Trần Cao Vân,Huế','0542228590'
EXEC InsertHocSinh 1,N'Nguyễn Phúc Thiện Phú',N'Nam',N'39 Lê Bá Bách,Huế','0544448590'

-- học sinh ở lớp 6B
EXEC InsertHocSinh 2,N'Lê Bá Quý',N'Nam',N'1 Huy Hiệu,Huế','0543848590'
EXEC InsertHocSinh 2,N'Nguyễn Thị Hải Lý',N'Nam',N'3 Nam Cao,Huế','0543111590'
EXEC InsertHocSinh 2,N'Trần Xuân An',N'Nam',N'8 Lý Thường Kiệt,Huế','0542228590'
EXEC InsertHocSinh 2,N'Nguyễn Quang Minh',N'Nam',N'11 Phan Chu Trinh,Huế','0542228590'
EXEC InsertHocSinh 2,N'Lê Văn Tuấn',N'Nam',N'41 Bến Nghé,Huế','0544448590'
GO
CREATE TABLE HocKy
(
	HocKyID int PRIMARY KEY IDENTITY(1,1),
	TenHocKy NVARCHAR(40)
)
GO
INSERT INTO HocKy
VALUES (N'Học Kỳ 1')
INSERT INTO HocKy
VALUES (N'Học Kỳ 2')
--SELECT * from HocKy
GO
CREATE TABLE Diem(
	DiemID int PRIMARY KEY IDENTITY(1,1),
	HocSinhID INT,
	MonhocID int,
	HocKyID INT,
	DiemMieng float,
	Diem15Lan1 float,
	Diem15Lan2 float,
	Diem1TietLan1 float,
	Diem1TietLan2 float,
	DiemThiCuoiKi float,
	FOREIGN KEY (HocSinhID) REFERENCES HocSinh(HocSinhID),
	FOREIGN KEY (MonhocID) REFERENCES Monhoc(MonhocID)  ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (HocKyID) REFERENCES HocKy(HocKyID)  ON UPDATE CASCADE ON DELETE CASCADE
)
GO
--SELECT * from diem
--lop 6A
-- học sinh 1
INSERT INTO Diem
VALUES (1,1,1,7,7,8,8,8,9)
INSERT INTO Diem
VALUES (1,2,1,7,7,6.5,8,7.5,9)
INSERT INTO Diem
VALUES (1,3,1,6,4,10,8,7,9)
INSERT INTO Diem
VALUES (1,4,1,10,10,9,9,9,9)
INSERT INTO Diem
VALUES (1,5,1,9,8,9,9,9,9)
INSERT INTO Diem
VALUES (1,6,1,5,5,6.5,4,4,9)
--học sinh 2
INSERT INTO Diem
VALUES (2,1,1,5,5,5,5,6,6)
INSERT INTO Diem
VALUES (2,2,1,5,5,5,4,9,9)
INSERT INTO Diem
VALUES (2,3,1,6,4,10,8,7,9)
INSERT INTO Diem
VALUES (2,4,1,10,5,9,5,9,9)
INSERT INTO Diem
VALUES (2,5,1,9,4,9,7,9,7)
INSERT INTO Diem
VALUES (2,6,1,9,5,5,4,5,9)
--học sinh 3
INSERT INTO Diem
VALUES (3,1,1,7,7,9,9,6,7)
INSERT INTO Diem
VALUES (3,2,1,9,9,4,5,7,9)
INSERT INTO Diem
VALUES (3,3,1,5,7,10,5,5,10)
INSERT INTO Diem
VALUES (3,4,1,10,4,6,9,6,9)
INSERT INTO Diem
VALUES (3,5,1,5,8,9,5,9,9)
INSERT INTO Diem
VALUES (3,6,1,5,5,6,4,6,9)
--học sinh 4
INSERT INTO Diem
VALUES (4,1,1,4,7,8,4,8,9)
INSERT INTO Diem
VALUES (4,2,1,7.5,7,5,8,8,9)
INSERT INTO Diem
VALUES (4,3,1,6,4,5,8,5,9)
INSERT INTO Diem
VALUES (4,4,1,10,7,9,9,7,9)
INSERT INTO Diem
VALUES (4,5,1,7,8,7,9,8,9)
INSERT INTO Diem
VALUES (4,6,1,8,8,5,4,7,9)
--học sinh 5
INSERT INTO Diem
VALUES (5,1,1,7,7,8,9,8,9)
INSERT INTO Diem
VALUES (5,2,1,7,7,6,8.5,7.5,9)
INSERT INTO Diem
VALUES (5,3,1,6,4,10,8.5,7,9.5)
INSERT INTO Diem
VALUES (5,4,1,7,6,9,9,4,9)
INSERT INTO Diem
VALUES (5,5,1,9,8,4,9,4,9)
INSERT INTO Diem
VALUES (5,6,1,10,5.5,6.5,4,8,9)


--lop 6B
-- học sinh 1
INSERT INTO Diem
VALUES (6,1,1,7,7,8,8,8,9)
INSERT INTO Diem
VALUES (6,2,1,7,7,6.5,8,7.5,9)
INSERT INTO Diem
VALUES (6,3,1,6,4,10,8,7,9)
INSERT INTO Diem
VALUES (6,4,1,10,10,9,9,9,9)
INSERT INTO Diem
VALUES (6,5,1,9,8,9,9,9,9)
INSERT INTO Diem
VALUES (6,6,1,5,5,6.5,4,4,9)
--học sinh 2
INSERT INTO Diem
VALUES (7,1,1,5,5,5,5,6,6)
INSERT INTO Diem
VALUES (7,2,1,5,5,5,4,9,9)
INSERT INTO Diem
VALUES (7,3,1,6,4,10,8,7,9)
INSERT INTO Diem
VALUES (7,4,1,10,5,9,5,9,9)
INSERT INTO Diem
VALUES (7,5,1,9,4,9,7,9,7)
INSERT INTO Diem
VALUES (7,6,1,9,5,5,4,5,9)
--học sinh 3
INSERT INTO Diem
VALUES (8,1,1,7,7,9,9,6,7)
INSERT INTO Diem
VALUES (8,2,1,9,9,4,5,7,9)
INSERT INTO Diem
VALUES (8,3,1,5,7,10,5,5,10)
INSERT INTO Diem
VALUES (8,4,1,10,4,6,9,6,9)
INSERT INTO Diem
VALUES (8,5,1,5,8,9,5,9,9)
INSERT INTO Diem
VALUES (8,6,1,5,5,6,4,6,9)
--học sinh 4
INSERT INTO Diem
VALUES (9,1,1,4,7,8,4,8,9)
INSERT INTO Diem
VALUES (9,2,1,7.5,7,5,8,8,9)
INSERT INTO Diem
VALUES (9,3,1,6,4,5,8,5,9)
INSERT INTO Diem
VALUES (9,4,1,10,7,9,9,7,9)
INSERT INTO Diem
VALUES (9,5,1,7,8,7,9,8,9)
INSERT INTO Diem
VALUES (9,6,1,8,8,5,4,7,9)
--học sinh 5
INSERT INTO Diem
VALUES (10,1,1,7,7,8,9,8,9)
INSERT INTO Diem
VALUES (10,2,1,7,7,6,8.5,7.5,9)
INSERT INTO Diem
VALUES (10,3,1,6,4,10,8.5,7,9.5)
INSERT INTO Diem
VALUES (10,4,1,7,6,9,9,4,9)
INSERT INTO Diem
VALUES (10,5,1,9,8,4,9,4,9)
INSERT INTO Diem
VALUES (10,6,1,10,5.5,6.5,4,8,9)


--điểm học kỳ 2
--lop 6A
-- học sinh 1
INSERT INTO Diem
VALUES (1,1,2,7,7,8,8,8,9)
INSERT INTO Diem
VALUES (1,2,2,7,7,6.5,8,7.5,9)
INSERT INTO Diem
VALUES (1,3,2,6,4,10,8,7,9)
INSERT INTO Diem
VALUES (1,4,2,10,10,9,9,9,9)
INSERT INTO Diem
VALUES (1,5,2,9,8,9,9,9,9)
INSERT INTO Diem
VALUES (1,6,2,5,5,6.5,4,4,9)
--học sinh 2
INSERT INTO Diem
VALUES (2,1,2,5,5,5,5,6,6)
INSERT INTO Diem
VALUES (2,2,2,5,5,5,4,9,9)
INSERT INTO Diem
VALUES (2,3,2,6,4,10,8,7,9)
INSERT INTO Diem
VALUES (2,4,2,10,5,9,5,9,9)
INSERT INTO Diem
VALUES (2,5,2,9,4,9,7,9,7)
INSERT INTO Diem
VALUES (2,6,2,9,5,5,4,5,9)
--học sinh 3
INSERT INTO Diem
VALUES (3,1,2,7,7,9,9,6,7)
INSERT INTO Diem
VALUES (3,2,2,9,9,4,5,7,9)
INSERT INTO Diem
VALUES (3,3,2,5,7,10,5,5,10)
INSERT INTO Diem
VALUES (3,4,2,10,4,6,9,6,9)
INSERT INTO Diem
VALUES (3,5,2,5,8,9,5,9,9)
INSERT INTO Diem
VALUES (3,6,2,5,5,6,4,6,9)
--học sinh 4
INSERT INTO Diem
VALUES (4,1,2,4,7,8,4,8,9)
INSERT INTO Diem
VALUES (4,2,2,7.5,7,5,8,8,9)
INSERT INTO Diem
VALUES (4,3,2,6,4,5,8,5,9)
INSERT INTO Diem
VALUES (4,4,2,10,7,9,9,7,9)
INSERT INTO Diem
VALUES (4,5,2,7,8,7,9,8,9)
INSERT INTO Diem
VALUES (4,6,2,8,8,5,4,7,9)
--học sinh 5
INSERT INTO Diem
VALUES (5,1,2,7,7,8,9,8,9)
INSERT INTO Diem
VALUES (5,2,2,7,7,6,8.5,7.5,9)
INSERT INTO Diem
VALUES (5,3,2,6,4,10,8.5,7,9.5)
INSERT INTO Diem
VALUES (5,4,2,7,6,9,9,4,9)
INSERT INTO Diem
VALUES (5,5,2,9,8,4,9,4,9)
INSERT INTO Diem
VALUES (5,6,2,10,5.5,6.5,4,8,9)


--lop 6B
-- học sinh 1
INSERT INTO Diem
VALUES (6,1,2,7,7,8,8,8,9)
INSERT INTO Diem
VALUES (6,2,2,7,7,6.5,8,7.5,9)
INSERT INTO Diem
VALUES (6,3,2,6,4,10,8,7,9)
INSERT INTO Diem
VALUES (6,4,2,10,10,9,9,9,9)
INSERT INTO Diem
VALUES (6,5,2,9,8,9,9,9,9)
INSERT INTO Diem
VALUES (6,6,2,5,5,6.5,4,4,9)
--học sinh 2
INSERT INTO Diem
VALUES (7,1,2,5,5,5,5,6,6)
INSERT INTO Diem
VALUES (7,2,2,5,5,5,4,9,9)
INSERT INTO Diem
VALUES (7,3,2,6,4,10,8,7,9)
INSERT INTO Diem
VALUES (7,4,2,10,5,9,5,9,9)
INSERT INTO Diem
VALUES (7,5,2,9,4,9,7,9,7)
INSERT INTO Diem
VALUES (7,6,2,9,5,5,4,5,9)
--học sinh 3
INSERT INTO Diem
VALUES (8,1,2,7,7,9,9,6,7)
INSERT INTO Diem
VALUES (8,2,2,9,9,4,5,7,9)
INSERT INTO Diem
VALUES (8,3,2,5,7,10,5,5,10)
INSERT INTO Diem
VALUES (8,4,2,10,4,6,9,6,9)
INSERT INTO Diem
VALUES (8,5,2,5,8,9,5,9,9)
INSERT INTO Diem
VALUES (8,6,2,5,5,6,4,6,9)
--học sinh 4
INSERT INTO Diem
VALUES (9,1,2,4,7,8,4,8,9)
INSERT INTO Diem
VALUES (9,2,2,7.5,7,5,8,8,9)
INSERT INTO Diem
VALUES (9,3,2,6,4,5,8,5,9)
INSERT INTO Diem
VALUES (9,4,2,10,7,9,9,7,9)
INSERT INTO Diem
VALUES (9,5,2,7,8,7,9,8,9)
INSERT INTO Diem
VALUES (9,6,2,8,8,5,4,7,9)
--học sinh 5
INSERT INTO Diem
VALUES (10,1,2,7,7,8,9,8,9)
INSERT INTO Diem
VALUES (10,2,2,7,7,6,8.5,7.5,9)
INSERT INTO Diem
VALUES (10,3,2,6,4,10,8.5,7,9.5)
INSERT INTO Diem
VALUES (10,4,2,7,6,9,9,4,9)
INSERT INTO Diem
VALUES (10,5,2,9,8,4,9,4,9)
INSERT INTO Diem
VALUES (10,6,2,10,5.5,6.5,4,8,9)


--select * from hocsinh
SELECT * from hocsinh,hocky,monhoc,diem
WHERE hocsinh.HocSinhID=diem.HocSinhID
AND hocky.HocKyID =diem.HocKyID
AND monhoc.MonhocID=diem.MonhocID

GO
CREATE TABLE Administrator(
	AdministratorID  int PRIMARY KEY IDENTITY(1,1),
	UserName NVARCHAR(50),
	pass nvarchar(20)
)
GO
INSERT INTO Administrator
VALUES ('Admin001','111111')
INSERT INTO Administrator
VALUES ('Admin002','222222')
INSERT INTO Administrator
VALUES ('Admin003','333333')
--SELECT * from Administrator
GO
-------------------------------------------------------
-----------------------PROCUDURE-----------------------
-------------------------------------------------------
-----------------------Admin---------------------------
-------------------------------------------------------
--Tạo thủ tục checkLogin -
CREATE PROC CheckAdminLogin
@UserName NVARCHAR(50),
@pass nvarchar(20)
AS
BEGIN
	SELECT * FROM Administrator WHERE UserName=@UserName AND pass=@pass
END
--EXEC CheckAdminLogin 'Admin001','111111'
GO
-----------------------AdminEnd------------------------

-----------------------Giáo viên---------------------------
-------------------------------------------------------
--select * from khoi
--Tạo thủ tục searchGiaovienByKhoiAndMonhoc -
CREATE PROC searchGiaovienByKhoiAndMonhoc
@TenKhoi NVARCHAR(50),
@TenMH nvarchar(20),
@TenGiaoVien NVARCHAR(50)
AS
BEGIN
	SELECT TenKhoi,TenMH,GiaoVienID,TenGiaoVien,Gioitinh,Diachi,SoDienThoai,Avatar 
	FROM Giaovien,Monhoc,Khoi 
	WHERE TenKhoi LIKE '%'+ @TenKhoi +'%' AND TenMH LIKE '%'+ @TenMH +'%' AND TenGiaoVien LIKE '%'+ @TenGiaoVien +'%'
	AND Khoi.KhoiID=Monhoc.KhoiID
	AND Monhoc.MonhocID=GiaoVien.MonhocID
END
-- exec searchGiaovienByKhoiAndMonhoc N'Khối lớp 6',N'','Bách'
GO
-- Tạo thủ tục update giáo viên
CREATE PROC updateGiaoVien
(
@GiaoVienID int ,
@TenMH NVARCHAR(40),
@TenGiaoVien NVARCHAR(50),
@Gioitinh nvarchar(5),
@Diachi nvarchar(50),
@SoDienThoai NVARCHAR(20),
@Avatar nvarchar(200) 
)
AS
BEGIN
	DECLARE @MonhocID INT
	SELECT @MonhocID=MonhocID FROM Monhoc WHERE TenMH=@TenMH
	UPDATE GiaoVien SET MonhocID=@MonhocID,TenGiaoVien=@TenGiaoVien,Gioitinh=@Gioitinh,
	Diachi=@Diachi,SoDienThoai=@SoDienThoai,Avatar=@Avatar
	WHERE GiaoVienID=@GiaoVienID
	
END
-- exec updateGiaoVien 1,N'Văn Lớp 6',N'lê bá bách',N'Nam',N'Huế','1111111',N'/Images/Avatar/NoAvatarSV.jpg'
--select * from giaovien
--EXEC CheckAdminLogin 'Admin001','111111'
GO
--select * from monhoc
-- tạo thủ tục thêm mới giáo viên
CREATE PROC insertGiaoVien
(
@TenKhoi NVARCHAR(50),
@TenMH NVARCHAR(50),
@TenGiaoVien NVARCHAR(50),
@Gioitinh nvarchar(5),
@Diachi nvarchar(50),
@SoDienThoai NVARCHAR(20),
@Avatar nvarchar(200)
)
AS
BEGIN	
	DECLARE @MonhocID INT
	SELECT @MonhocID=MonhocID FROM Monhoc,Khoi 
	WHERE TenMH=@TenMH AND TenKhoi=@TenKhoi AND Khoi.KhoiID=Monhoc.KhoiID
	INSERT INTO GiaoVien
	VALUES(@MonhocID,@TenGiaoVien,@Gioitinh,@Diachi,@SoDienThoai,@Avatar)
END
-- EXEC insertGiaoVien N'Khối lớp 6',N'Toán học Lớp 6',N'Lê Bá Quỳnh Lý',N'Nữ',N'Huế',N'99999999',N'/Images/Avatar/NoAvatarSV.jpg'
--select * FROM giaovien
-----------------------GiaovienEnd------------------------


-----------------------HocSinh-------------------------
-------------------------------------------------------
GO
--select * from hocsinh
CREATE PROC searchHocSinhByTenKhoiTenLopTenHocSinh
@TenKhoi NVARCHAR(50),
@Tenlop NVARCHAR(50),
@Ten NVARCHAR(50)
AS
BEGIN
	SELECT TenKhoi,Tenlop,HocSinh.HocSinhID,Ten,GioiTinh,Diachi,SoDienThoai,Avatar 
	FROM Khoi,Lop,HocSinh
	WHERE TenKhoi LIKE '%'+ @TenKhoi+ '%'
	AND Tenlop LIKE '%'+ @Tenlop+ '%' 
	AND Ten LIKE '%'+ @Ten+ '%' 
	AND Khoi.KhoiID=Lop.KhoiID
	AND Lop.LopID=HocSinh.LopID
END

--EXEC searchHocSinhByTenKhoiTenLopTenHocSinh N'Khối lớp 6','',N''
GO
/*
HocSinhID int PRIMARY KEY IDENTITY(1,1),
	LopID int,
	Ten nvarchar(50),
	GioiTinh nvarchar(5),
	Diachi nvarchar(50),
	SoDienThoai NVARCHAR(20),
	Avatar nvarchar(200) DEFAULT(N'/Images/Sinhvien/NoAvatarSV.jpg'),
*/
-- select * from hocsinh
--thủ tục update Học Sinh
CREATE PROC UpdateHocSinh
	@HocSinhID INT,
	@TenKhoi NVARCHAR(50),
	@TenLop nvarchar(50),
	@Ten NVARCHAR(50),
	@GioiTinh nvarchar(5),
	@Diachi nvarchar(50),
	@SoDienThoai NVARCHAR(20),
	@Avatar nvarchar(200)
AS
BEGIN
	DECLARE @LopID INT
	SELECT @LopID=LopID FROM Lop,Khoi WHERE TenKhoi=@TenKhoi 
	AND Lop.KhoiID=Khoi.KhoiID AND TenLop=@TenLop 
	UPDATE HocSinh SET LopID=@LopID,Ten=@Ten,GioiTinh=@GioiTinh,
	Diachi=@Diachi,SoDienThoai=@SoDienThoai,Avatar=@Avatar
	WHERE HocSinhID=@HocSinhID
END 
--EXEC UpdateHocSinh 1, N'Khối lớp 6','6A',N'Nguyễn Phúc Thiện Phúcccc','Nam',N'Quảng Nam','9999999',N'/Images/Avatar/NoAvatarSV.jpg'

--select * from hocsinh
GO --thủ tục insert học sinh
CREATE PROC procInsertHocSinh
@TenKhoi NVARCHAR(50),
@TenLop nvarchar(50),
@Ten nvarchar(50),
@GioiTinh nvarchar(5),
@Diachi nvarchar(50),
@SoDienThoai NVARCHAR(20),
@Avatar NVARCHAR(200)
AS
BEGIN
	DECLARE @LopID INT 
	SELECT @LopID=LopID FROM Lop,Khoi WHERE TenKhoi=@TenKhoi 
	AND Lop.KhoiID=Khoi.KhoiID AND TenLop=@TenLop 
	INSERT INTO HocSinh (LopID,Ten,GioiTinh,Diachi,SoDienThoai,Avatar)
	VALUES (@LopID,@Ten,@GioiTinh,@Diachi,@SoDienThoai,@Avatar)
	UPDATE Lop SET siso=siso+1 WHERE LopID=@LopID
END
--EXEC procInsertHocSinh
--select *from hocsinh
GO
-- Tạo thủ tục tìm kiếm học sinh bởi ID
CREATE PROC searchHocSinhByHocSinhID
@HocSinhID INT
AS
BEGIN
	SELECT Ten,TenLop,TenKhoi,Khoi.KhoiID FROM HocSinh,Lop,Khoi	
	WHERE HocSinh.LopID=Lop.LopID
	AND Lop.KhoiID=Khoi.KhoiID
	AND HocSinhID=@HocSinhID
END
--EXEC searchHocSinhByHocSinhID 1

GO
-----------------------HocSinhEND----------------------

-----------------------Môn Học---------------------------
-------------------------------------------------------
--Tạo thủ tục checkLogin -
CREATE PROC searchMonhocKhoiTenMH
@TenKhoi NVARCHAR(50),
@TenMH nvarchar(30)
AS
BEGIN
	SELECT TenKhoi,MonhocID,TenMH,HeSo FROM Khoi,Monhoc 
	WHERE TenKhoi LIKE '%'+ @TenKhoi+ '%' AND TenMH LIKE '%'+ @TenMH+ '%'
	AND Monhoc.KhoiID=Khoi.KhoiID
END
--EXEC searchMonhocKhoiTenMH N'Khối lớp 6',N''
--select * from monhoc
GO
-- Tìm kiếm môn học dựa vào mã môn học
CREATE PROC searchMonhocIDByKhoiID
@KhoiID INT
AS
BEGIN
	SELECT MonhocID FROM Monhoc,Khoi
	WHERE Monhoc.KhoiID=Khoi.KhoiID
	AND Khoi.KhoiID=@KhoiID
END
--EXEC searchMonhocIDByKhoiID 1
--select * from monhoc
GO
-- tạo thủ tục update môn học
CREATE PROC updateMonhoc
@MonhocID INT ,
@TenKhoi NVARCHAR(20),
@TenMH nvarchar(30),
@HeSo int
AS
BEGIN
	DECLARE @KhoiID INT
	SELECT @KhoiID=KhoiID FROM Khoi
	WHERE TenKhoi=@TenKhoi
	UPDATE Monhoc SET KhoiID=@KhoiID,TenMH=@TenMH,HeSo=@HeSo
	WHERE MonhocID=@MonhocID
END
-- EXEC updateMonhoc 1,N'Khối lớp 7',N'Toán học lớp 6',2
GO
-- Tạo thủ tục chèn môn học
CREATE PROC insertMonhoc
(
@TenKhoi NVARCHAR(20),
@TenMH nvarchar(30),
@HeSo int
)
AS
BEGIN
	DECLARE @KhoiID INT
	SELECT @KhoiID=KhoiID FROM Khoi
	WHERE TenKhoi=@TenKhoi
	INSERT INTO Monhoc
	VALUES (@TenMH,@HeSo,@KhoiID)
END
GO
--EXEC insertMonhoc N'Khối lớp 6',N'Toán đại số',2
-- tạo thủ tục xóa môn học 
CREATE PROC deleteMonhoc
@MonhocID INT
AS
BEGIN
	DELETE FROM Monhoc WHERE MonhocID=@MonhocID
END
GO

--select * from monhoc
-----------------------MonHocEnd------------------------

-----------------------PROCEDURE Lop--------------------
--------------------------------------------------------
GO
CREATE PROC searchLopByTenKhoiAndTenLop
@TenKhoi NVARCHAR(50),
@Tenlop NVARCHAR(50)
AS
BEGIN
	SELECT LopID,TenKhoi,TenLop,siso,NamHoc FROM Khoi,Lop 
	WHERE TenKhoi LIKE '%'+ @TenKhoi+ '%'
	AND Tenlop LIKE '%'+ @Tenlop+ '%' 
	AND Khoi.KhoiID=Lop.KhoiID
END
GO
-- EXEC searchLopByTenKhoiAndTenLop N'Khối lớp 6','6A' 
GO
-- Cập nhật lại lớp
CREATE PROC UpdateLop
@LopID int ,
@TenKhoi NVARCHAR(50),
@Tenlop nvarchar(20),
@siso int,
@NamHoc NVARCHAR(10)
AS
BEGIN
	DECLARE @KhoiID INT
	SELECT @KhoiID=KhoiID FROM Khoi 
	WHERE TenKhoi=@TenKhoi
	UPDATE Lop SET KhoiID=@KhoiID,Tenlop=@Tenlop,siso=@siso,NamHoc=@NamHoc
	WHERE LopID=@LopID
END

-- EXEC UpdateLop 3,N'Khối lớp 6',N'79',2,'2011'
GO
-- Tạo thủ tục thêm mới lớp
CREATE PROC insertLop
@TenKhoi NVARCHAR(50),
@Tenlop nvarchar(20),
@NamHoc NVARCHAR(10)
AS
BEGIN
	DECLARE @KhoiID INT
	SELECT @KhoiID=KhoiID FROM Khoi 
	WHERE TenKhoi=@TenKhoi
	INSERT INTO Lop
	VALUES (@KhoiID,@Tenlop,0,@NamHoc)
END
GO
-- EXEC insertLop  N'Khối lớp 6','6D','2011'
select * FROM Lop
GO
-- tạo thủ tục xóa lớp
CREATE PROC deleteLop
@LopID INT
AS
BEGIN
	DELETE FROM Lop WHERE LopID=@LopID
END
GO
SELECT * from lop
GO
------------------------LopEND--------------------------

-----------------------PROCEDURE Điểm--------------------
--------------------------------------------------------
--select * from monhoc
CREATE PROC searchDiemByTenKhoiTenLopMonhocHocKyTenHS
@TenKhoi NVARCHAR(50),
@Tenlop NVARCHAR(50),
@TenMH NVARCHAR(30),
@TenHocKy NVARCHAR(40),
@Ten NVARCHAR(50)
AS
BEGIN
	SELECT TenMH,HeSo,HocSinh.HocSinhID,Ten,Tenlop,TenKhoi,TenHocKy,DiemMieng,Diem15Lan1,Diem15Lan2,Diem1TietLan1,Diem1TietLan2,DiemThiCuoiKi FROM Khoi,Lop,Monhoc,HocKy,HocSinh,Diem 
	WHERE TenKhoi LIKE '%'+ @TenKhoi+ '%'
	AND Tenlop LIKE '%'+ @Tenlop+ '%' 
	AND Ten LIKE '%'+ @Ten+ '%'
	AND TenMH LIKE '%'+ @TenMH+ '%'
	AND TenHocKy LIKE '%'+ @TenHocKy+ '%'
	AND Khoi.KhoiID=Lop.KhoiID
	AND	Lop.LopID=HocSinh.LopID
	AND Diem.HocSinhID =HocSinh.HocSinhID
	AND diem.monhocid=monhoc.monhocid
	AND hocky.hockyid=diem.hockyid
END
GO
-- EXEC searchDiemByTenKhoiTenLopMonhocHocKyTenHS N'Khối lớp 6','6A',N'',N'',N'' 
--select * from monhoc
GO
--tạo thủ tục chèn điểm cho học sinh
CREATE PROC InsertDiemHocSinh
(
	@HocSinhID INT,
	@MonhocID int,
	@HocKyID INT,
	@DiemMieng float,
	@Diem15Lan1 float,
	@Diem15Lan2 float,
	@Diem1TietLan1 float,
	@Diem1TietLan2 float,
	@DiemThiCuoiKi float
)
AS
BEGIN
	INSERT INTO Diem
	VALUES (@HocSinhID,@MonhocID,@HocKyID,@DiemMieng,@Diem15Lan1,@Diem15Lan2,@Diem1TietLan1,@Diem1TietLan2,@DiemThiCuoiKi)
END
-- EXEC InsertDiemHocSinh 1,1,1,7,7,8,8,8,10
--select * from diem
GO
select tenmh,ten,Tenlop,tenhocky,diemmieng FROM hocsinh,diem,monhoc,hocky,lop 
where 
hocsinh.hocsinhid=diem.hocsinhid 
GO
select * from diem
GO
--Cập nhật điểm lại
CREATE PROC UpdateDiemHs
@TenKhoi NVARCHAR(50),
@Tenlop NVARCHAR(50),
@TenMH NVARCHAR(30),
@TenHocKy NVARCHAR(40),
@Ten NVARCHAR(50),
@DiemMieng float,
@Diem15Lan1 float,
@Diem15Lan2 float,
@Diem1TietLan1 float,
@Diem1TietLan2 float,
@DiemThiCuoiKi float
AS
BEGIN
	DECLARE @DiemID INT
	SELECT @DiemID=DiemID FROM Khoi,Lop,Monhoc,HocKy,HocSinh,Diem 
	WHERE TenKhoi = @TenKhoi
	AND Tenlop = @Tenlop 
	AND Ten = @Ten
	AND TenMH = @TenMH
	AND TenHocKy = @TenHocKy
	AND Khoi.KhoiID=Lop.KhoiID
	AND	Lop.LopID=HocSinh.LopID
	AND Diem.HocSinhID =HocSinh.HocSinhID
	AND diem.monhocid=monhoc.monhocid
	AND hocky.hockyid=diem.hockyid
	UPDATE Diem SET DiemMieng=@DiemMieng,Diem15Lan1=@Diem15Lan1,Diem15Lan2=@Diem15Lan2,
	Diem1TietLan1=@Diem1TietLan1,Diem1TietLan2=@Diem1TietLan2,DiemThiCuoiKi=@DiemThiCuoiKi
	WHERE DiemID=@DiemID
	
END
--EXEC UpdateDiemHs N'Khối lớp 6',N'6A',N'Toán Lớp 6',N'Học Kỳ 1',N'Nguyễn Xuân Trường',8,8,8,8,8,8 
GO
-- tạo thủ tục xóa điểm học sinh
CREATE PROC deleteDiemHs
@TenKhoi NVARCHAR(50),
@Tenlop NVARCHAR(50),
@TenMH NVARCHAR(30),
@TenHocKy NVARCHAR(40),
@Ten NVARCHAR(50),
@DiemMieng float,
@Diem15Lan1 float,
@Diem15Lan2 float,
@Diem1TietLan1 float,
@Diem1TietLan2 float,
@DiemThiCuoiKi float
AS
BEGIN
	DECLARE @DiemID INT
	SELECT @DiemID=DiemID FROM Khoi,Lop,Monhoc,HocKy,HocSinh,Diem 
	WHERE TenKhoi = @TenKhoi
	AND Tenlop = @Tenlop 
	AND Ten = @Ten
	AND TenMH = @TenMH
	AND TenHocKy = @TenHocKy
	AND Khoi.KhoiID=Lop.KhoiID
	AND	Lop.LopID=HocSinh.LopID
	AND Diem.HocSinhID =HocSinh.HocSinhID
	AND diem.monhocid=monhoc.monhocid
	AND hocky.hockyid=diem.hockyid
	AND DiemMieng=@DiemMieng AND Diem15Lan1=@Diem15Lan1 AND Diem15Lan2=@Diem15Lan2
	AND Diem1TietLan1=@Diem1TietLan1 AND Diem1TietLan2=@Diem1TietLan2 AND DiemThiCuoiKi=@DiemThiCuoiKi
	
	DELETE FROM Diem WHERE DiemID=@DiemID
END
GO
-- EXEC deleteDiemHs N'Khối lớp 6',N'6A',N'Thể Dục Lớp 6',N'Học Kỳ 2',N'Nguyễn Xuân Trường',5,1,1,1,1,1
--EXEC searchDiemByTenKhoiTenLopMonhocHocKyTenHS N'Khối lớp 6','6A',N'',N'',N'' 
-----------------------DiemEND--------------------------


GO
--điểm miệng *1
--điểm Diem15Lan1 *1
--điểm Diem15Lan2 *1
--điểm Diem1TietLan1 *2
--điểm Diem1TietLan2 *2

--==>A trung bình



--điểm DiemThiCuoiKi==>B

--(A*2+B)/3

--(điểm kì 2 *2 +điểm kì 1)/3


--


