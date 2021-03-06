CREATE DATABASE StudentManagement
GO
-- drop database StudentManagement
USE StudentManagement
GO
CREATE TABLE Truong(
	matruong INT PRIMARY KEY,
	tentruong nvarchar(50)
)
GO
-- INSERT INTO Truong(tentruong) SELECT 'ABC'+CONVERT(varchar(20),GETDATE(),112)
-- Chen du lieu vao bang Truong
INSERT INTO Truong VALUES(1,N'Đại học Bách Khoa Đà Nẵng')
INSERT INTO Truong VALUES(2,N'Đại học Kinh Tế Đà Nẵng')
INSERT INTO Truong VALUES(3,N'Đại học Sư Phạm Đà Nẵng')
INSERT INTO Truong VALUES(4,N'Cao đẳng công nghệ')
INSERT INTO Truong VALUES(5,N'Cao đẳng Kinh Tế Kế Hoạch')
GO
CREATE TABLE Khoa(
	makhoa int PRIMARY KEY,
	tenkhoa nvarchar(50),
	matruong int,
	kyhieu nvarchar(5),
	FOREIGN KEY(matruong) REFERENCES Truong(matruong) ON UPDATE CASCADE ON DELETE CASCADE
)
GO
-- Ma khoa phai nhap bang tay vi khoa la co dinh khong the thay bag ma so khac vi vay moi khoa co 1 ma so dc cap rieng tuong ung voi truong hien tai
-- Chen du lieu vao bang Khoa
	-- Khoa cua truong DHBK
	INSERT INTO Khoa VALUES(101,N'Cơ Khí Chế Tạo',1,'C')
	INSERT INTO Khoa VALUES(102,N'Điện Kỹ Thuật',1,'D')
	INSERT INTO Khoa VALUES(103,N'Điện Tử Viễn Thông',1,'DT')
	INSERT INTO Khoa VALUES(104,N'Xây Dựng Dân Dụng và Công Nghiệp',1,'XD')
	INSERT INTO Khoa VALUES(109,N'Công Nghệ Thông Tin',1,'T')
	-- Khoa cua truong DHKT
	INSERT INTO Khoa VALUES(207,N'Khoa Kinh Tế',2,'K')
	INSERT INTO Khoa VALUES(209,N'Khoa Mac-Lênin',2,'ML')
	INSERT INTO Khoa VALUES(217,N'Khoa Tài Chính - Ngân Hàng',2,'TC')
	INSERT INTO Khoa VALUES(205,N'Khoa Kế Toán',2,'KT')
	INSERT INTO Khoa VALUES(203,N'Khoa Quản Trị Kinh Doanh',2,'QT')	
	-- Khoa cua truong DHSP
	INSERT INTO Khoa VALUES(319,N'Khoa Toán',3,'ST')
	INSERT INTO Khoa VALUES(305,N'Khoa Lý',3,'SVL')
	INSERT INTO Khoa VALUES(306,N'Khoa Hóa',3,'SHH')
	INSERT INTO Khoa VALUES(307,N'Khoa Sinh',3,'SSS')
	INSERT INTO Khoa VALUES(312,N'Khoa Tin',3,'SPT')
	-- Khoa cua truong CDCN
	INSERT INTO Khoa VALUES(401,N'Cơ Khí Chế Tạo',4,'C')
	INSERT INTO Khoa VALUES(402,N'Điện Kỹ Thuật',4,'D')
	INSERT INTO Khoa VALUES(403,N'Điện Tử Viễn Thông',4,'DT')
	INSERT INTO Khoa VALUES(404,N'Xây Dựng Dân Dụng và Công Nghiệp',4,'XD')
	INSERT INTO Khoa VALUES(409,N'Công Nghệ Thông Tin',4,'T')
	-- Khoa cua truong CDKTKH
	INSERT INTO Khoa VALUES(507,N'Khoa Kinh Tế',5,'K')
	INSERT INTO Khoa VALUES(509,N'Khoa Mac-Lênin',5,'ML')
	INSERT INTO Khoa VALUES(517,N'Khoa Tài Chính - Ngân Hàng',5,'TC')
	INSERT INTO Khoa VALUES(505,N'Khoa Kế Toán',5,'KT')
	INSERT INTO Khoa VALUES(503,N'Khoa Quản Trị Kinh Doanh',5,'QT')	
GO

CREATE TABLE Hedaotao(
	Madaotao int PRIMARY KEY,
	Tendaotao nvarchar(20)
)
GO
-- Chen du lieu vao bang Hedaotao
INSERT INTO Hedaotao VALUES(1,N'Chính quy')
INSERT INTO Hedaotao VALUES(2,N'Liên Thông')
INSERT INTO Hedaotao VALUES(3,N'Tại Chức')

GO

CREATE TABLE Lop(
	malop int PRIMARY KEY IDENTITY(1,1),
	tenlop nvarchar(20),
	makhoa int,
	khoahoc nvarchar(10),
	hedaotao int,
	siso int DEFAULT(0),
	FOREIGN KEY (makhoa) REFERENCES Khoa(makhoa) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (hedaotao) REFERENCES Hedaotao(Madaotao)
)

-- Tao thu tuc chen vao 1 lop
CREATE PROCEDURE InsertLop
	@makhoa int,
	@khoahoc varchar(10),
	@hedaotao nvarchar(10)
AS
	BEGIN
		DECLARE @_khoahoc varchar(10),@madaotao int,@kyhieu varchar(5),@tenlop varchar(20),@tenlopmoi varchar(20),@so int,@sokytu int,@STT int
		SET @_khoahoc = SUBSTRING(@khoahoc,3,2) -- lay 2 ky tu cuoi cua nam hoc
		SELECT @madaotao = Madaotao FROM Hedaotao WHERE Tendaotao = @hedaotao
		SELECT @kyhieu = kyhieu FROM Khoa WHERE makhoa = @makhoa
		SELECT @tenlop = tenlop FROM Lop WHERE makhoa = @makhoa AND khoahoc = @khoahoc ORDER BY tenlop
		IF(@tenlop is null)
			BEGIN
				SET @tenlopmoi = @_khoahoc + @kyhieu + '1'
				INSERT INTO Lop(tenlop,makhoa,khoahoc,hedaotao) VALUES(@tenlopmoi,@makhoa,@khoahoc,@madaotao)
			END
		ELSE
			BEGIN
				SET @sokytu = LEN(@_khoahoc+@kyhieu)
				-- Cat chuoi ten lop ra VD : 07T1 ta cat ra con lai 1 
				-- Sau do chuyen 1 thanh so nguyen roi + 1 la dc STT lop
				SET @STT = CAST(SUBSTRING(@tenlop,@sokytu+1,1) AS INT) + 1
				-- Thiet lap ten lop moi = 'khoahoc'+'kyhieu'+'STT'(chuyen int to nvarchar)
				SET @tenlopmoi = @_khoahoc + @kyhieu + CAST(@STT AS NVARCHAR(3))
				INSERT INTO Lop(tenlop,makhoa,khoahoc,hedaotao) VALUES(@tenlopmoi,@makhoa,@khoahoc,@madaotao)
			END
	END
GO
-- Chen du lieu vao bang Lop dua tren thu tuc InsertLop
	-- Chen LOP cho truong Dai Hoc Bach Khoa
/*
	exec InsertLop 101,'2007',N'Chính quy'
	exec InsertLop 101,'2007',N'Chính quy'
	exec InsertLop 101,'2008',N'Chính quy'
	exec InsertLop 101,'2008',N'Chính quy'
	exec InsertLop 101,'2009',N'Chính quy'
	exec InsertLop 101,'2009',N'Chính quy'
	exec InsertLop 101,'2010',N'Chính quy'
	exec InsertLop 101,'2010',N'Chính quy'
	exec InsertLop 101,'2011',N'Chính quy'
	exec InsertLop 101,'2011',N'Chính quy'
	
	exec InsertLop 102,'2007',N'Chính quy'
	exec InsertLop 102,'2007',N'Chính quy'
	exec InsertLop 102,'2008',N'Chính quy'
	exec InsertLop 102,'2008',N'Chính quy'
	exec InsertLop 102,'2009',N'Chính quy'
	exec InsertLop 102,'2009',N'Chính quy'
	exec InsertLop 102,'2010',N'Chính quy'
	exec InsertLop 102,'2010',N'Chính quy'
	exec InsertLop 102,'2011',N'Chính quy'
	exec InsertLop 102,'2011',N'Chính quy'
	
	exec InsertLop 103,'2007',N'Chính quy'
	exec InsertLop 103,'2007',N'Chính quy'
	exec InsertLop 103,'2008',N'Chính quy'
	exec InsertLop 103,'2008',N'Chính quy'
	exec InsertLop 103,'2009',N'Chính quy'
	exec InsertLop 103,'2009',N'Chính quy'
	exec InsertLop 103,'2010',N'Chính quy'
	exec InsertLop 103,'2010',N'Chính quy'
	exec InsertLop 103,'2011',N'Chính quy'
	exec InsertLop 103,'2011',N'Chính quy'
	
	exec InsertLop 104,'2007',N'Chính quy'
	exec InsertLop 104,'2007',N'Chính quy'
	exec InsertLop 104,'2008',N'Chính quy'
	exec InsertLop 104,'2008',N'Chính quy'
	exec InsertLop 104,'2009',N'Chính quy'
	exec InsertLop 104,'2009',N'Chính quy'
	exec InsertLop 104,'2010',N'Chính quy'
	exec InsertLop 104,'2010',N'Chính quy'
	exec InsertLop 104,'2011',N'Chính quy'
	exec InsertLop 104,'2011',N'Chính quy'
	
	exec InsertLop 109,'2007',N'Chính quy'
	exec InsertLop 109,'2007',N'Chính quy'
	exec InsertLop 109,'2008',N'Chính quy'
	exec InsertLop 109,'2008',N'Chính quy'
	exec InsertLop 109,'2009',N'Chính quy'
	exec InsertLop 109,'2009',N'Chính quy'
	exec InsertLop 109,'2010',N'Chính quy'
	exec InsertLop 109,'2010',N'Chính quy'
	exec InsertLop 109,'2011',N'Chính quy'
	exec InsertLop 109,'2011',N'Chính quy'
	
	SELECT * FROM Lop
*/

-- Chen LOP cho truong Dai Hoc Kinh Te
/*
	exec InsertLop 207,'2007',N'Chính quy'
	exec InsertLop 207,'2007',N'Chính quy'
	exec InsertLop 207,'2008',N'Chính quy'
	exec InsertLop 207,'2008',N'Chính quy'
	exec InsertLop 207,'2009',N'Chính quy'
	exec InsertLop 207,'2009',N'Chính quy'
	exec InsertLop 207,'2010',N'Chính quy'
	exec InsertLop 207,'2010',N'Chính quy'
	exec InsertLop 207,'2011',N'Chính quy'
	exec InsertLop 207,'2011',N'Chính quy'
	
	exec InsertLop 209,'2007',N'Chính quy'
	exec InsertLop 209,'2007',N'Chính quy'
	exec InsertLop 209,'2008',N'Chính quy'
	exec InsertLop 209,'2008',N'Chính quy'
	exec InsertLop 209,'2009',N'Chính quy'
	exec InsertLop 209,'2009',N'Chính quy'
	exec InsertLop 209,'2010',N'Chính quy'
	exec InsertLop 209,'2010',N'Chính quy'
	exec InsertLop 209,'2011',N'Chính quy'
	exec InsertLop 209,'2011',N'Chính quy'
	
	exec InsertLop 217,'2007',N'Chính quy'
	exec InsertLop 217,'2007',N'Chính quy'
	exec InsertLop 217,'2008',N'Chính quy'
	exec InsertLop 217,'2008',N'Chính quy'
	exec InsertLop 217,'2009',N'Chính quy'
	exec InsertLop 217,'2009',N'Chính quy'
	exec InsertLop 217,'2010',N'Chính quy'
	exec InsertLop 217,'2010',N'Chính quy'
	exec InsertLop 217,'2011',N'Chính quy'
	exec InsertLop 217,'2011',N'Chính quy'
	
	exec InsertLop 205,'2007',N'Chính quy'
	exec InsertLop 205,'2007',N'Chính quy'
	exec InsertLop 205,'2008',N'Chính quy'
	exec InsertLop 205,'2008',N'Chính quy'
	exec InsertLop 205,'2009',N'Chính quy'
	exec InsertLop 205,'2009',N'Chính quy'
	exec InsertLop 205,'2010',N'Chính quy'
	exec InsertLop 205,'2010',N'Chính quy'
	exec InsertLop 205,'2011',N'Chính quy'
	exec InsertLop 205,'2011',N'Chính quy'
	
	exec InsertLop 203,'2007',N'Chính quy'
	exec InsertLop 203,'2007',N'Chính quy'
	exec InsertLop 203,'2008',N'Chính quy'
	exec InsertLop 203,'2008',N'Chính quy'
	exec InsertLop 203,'2009',N'Chính quy'
	exec InsertLop 203,'2009',N'Chính quy'
	exec InsertLop 203,'2010',N'Chính quy'
	exec InsertLop 203,'2010',N'Chính quy'
	exec InsertLop 203,'2011',N'Chính quy'
	exec InsertLop 203,'2011',N'Chính quy'
	
	SELECT * FROM Lop
*/

-- Chen LOP cho truong Dai Hoc Su Pham
/*
	exec InsertLop 319,'2007',N'Chính quy'
	exec InsertLop 319,'2007',N'Chính quy'
	exec InsertLop 319,'2008',N'Chính quy'
	exec InsertLop 319,'2008',N'Chính quy'
	exec InsertLop 319,'2009',N'Chính quy'
	exec InsertLop 319,'2009',N'Chính quy'
	exec InsertLop 319,'2010',N'Chính quy'
	exec InsertLop 319,'2010',N'Chính quy'
	exec InsertLop 319,'2011',N'Chính quy'
	exec InsertLop 319,'2011',N'Chính quy'
	
	exec InsertLop 305,'2007',N'Chính quy'
	exec InsertLop 305,'2007',N'Chính quy'
	exec InsertLop 305,'2008',N'Chính quy'
	exec InsertLop 305,'2008',N'Chính quy'
	exec InsertLop 305,'2009',N'Chính quy'
	exec InsertLop 305,'2009',N'Chính quy'
	exec InsertLop 305,'2010',N'Chính quy'
	exec InsertLop 305,'2010',N'Chính quy'
	exec InsertLop 305,'2011',N'Chính quy'
	exec InsertLop 305,'2011',N'Chính quy'
	
	exec InsertLop 306,'2007',N'Chính quy'
	exec InsertLop 306,'2007',N'Chính quy'
	exec InsertLop 306,'2008',N'Chính quy'
	exec InsertLop 306,'2008',N'Chính quy'
	exec InsertLop 306,'2009',N'Chính quy'
	exec InsertLop 306,'2009',N'Chính quy'
	exec InsertLop 306,'2010',N'Chính quy'
	exec InsertLop 306,'2010',N'Chính quy'
	exec InsertLop 306,'2011',N'Chính quy'
	exec InsertLop 306,'2011',N'Chính quy'
	
	exec InsertLop 307,'2007',N'Chính quy'
	exec InsertLop 307,'2007',N'Chính quy'
	exec InsertLop 307,'2008',N'Chính quy'
	exec InsertLop 307,'2008',N'Chính quy'
	exec InsertLop 307,'2009',N'Chính quy'
	exec InsertLop 307,'2009',N'Chính quy'
	exec InsertLop 307,'2010',N'Chính quy'
	exec InsertLop 307,'2010',N'Chính quy'
	exec InsertLop 307,'2011',N'Chính quy'
	exec InsertLop 307,'2011',N'Chính quy'
	
	exec InsertLop 312,'2007',N'Chính quy'
	exec InsertLop 312,'2007',N'Chính quy'
	exec InsertLop 312,'2008',N'Chính quy'
	exec InsertLop 312,'2008',N'Chính quy'
	exec InsertLop 312,'2009',N'Chính quy'
	exec InsertLop 312,'2009',N'Chính quy'
	exec InsertLop 312,'2010',N'Chính quy'
	exec InsertLop 312,'2010',N'Chính quy'
	exec InsertLop 312,'2011',N'Chính quy'
	exec InsertLop 312,'2011',N'Chính quy'
	
	SELECT * FROM Lop
*/

-- Chen LOP cho truong Cao Dang Cong Nghe
/*
	exec InsertLop 401,'2007',N'Chính quy'
	exec InsertLop 401,'2007',N'Chính quy'
	exec InsertLop 401,'2008',N'Chính quy'
	exec InsertLop 401,'2008',N'Chính quy'
	exec InsertLop 401,'2009',N'Chính quy'
	exec InsertLop 401,'2009',N'Chính quy'
	exec InsertLop 401,'2010',N'Chính quy'
	exec InsertLop 401,'2010',N'Chính quy'
	exec InsertLop 401,'2011',N'Chính quy'
	exec InsertLop 401,'2011',N'Chính quy'
	
	exec InsertLop 402,'2007',N'Chính quy'
	exec InsertLop 402,'2007',N'Chính quy'
	exec InsertLop 402,'2008',N'Chính quy'
	exec InsertLop 402,'2008',N'Chính quy'
	exec InsertLop 402,'2009',N'Chính quy'
	exec InsertLop 402,'2009',N'Chính quy'
	exec InsertLop 402,'2010',N'Chính quy'
	exec InsertLop 402,'2010',N'Chính quy'
	exec InsertLop 402,'2011',N'Chính quy'
	exec InsertLop 402,'2011',N'Chính quy'
	
	exec InsertLop 403,'2007',N'Chính quy'
	exec InsertLop 403,'2007',N'Chính quy'
	exec InsertLop 403,'2008',N'Chính quy'
	exec InsertLop 403,'2008',N'Chính quy'
	exec InsertLop 403,'2009',N'Chính quy'
	exec InsertLop 403,'2009',N'Chính quy'
	exec InsertLop 403,'2010',N'Chính quy'
	exec InsertLop 403,'2010',N'Chính quy'
	exec InsertLop 403,'2011',N'Chính quy'
	exec InsertLop 403,'2011',N'Chính quy'
	
	exec InsertLop 404,'2007',N'Chính quy'
	exec InsertLop 404,'2007',N'Chính quy'
	exec InsertLop 404,'2008',N'Chính quy'
	exec InsertLop 404,'2008',N'Chính quy'
	exec InsertLop 404,'2009',N'Chính quy'
	exec InsertLop 404,'2009',N'Chính quy'
	exec InsertLop 404,'2010',N'Chính quy'
	exec InsertLop 404,'2010',N'Chính quy'
	exec InsertLop 404,'2011',N'Chính quy'
	exec InsertLop 404,'2011',N'Chính quy'
	
	exec InsertLop 409,'2007',N'Chính quy'
	exec InsertLop 409,'2007',N'Chính quy'
	exec InsertLop 409,'2008',N'Chính quy'
	exec InsertLop 409,'2008',N'Chính quy'
	exec InsertLop 409,'2009',N'Chính quy'
	exec InsertLop 409,'2009',N'Chính quy'
	exec InsertLop 409,'2010',N'Chính quy'
	exec InsertLop 409,'2010',N'Chính quy'
	exec InsertLop 409,'2011',N'Chính quy'
	exec InsertLop 409,'2011',N'Chính quy'
	
	SELECT * FROM Lop
*/

-- Chen LOP cho truong Cao Dang Kinh Te Ke Hoach
/*
	exec InsertLop 507,'2007',N'Chính quy'
	exec InsertLop 507,'2007',N'Chính quy'
	exec InsertLop 507,'2008',N'Chính quy'
	exec InsertLop 507,'2008',N'Chính quy'
	exec InsertLop 507,'2009',N'Chính quy'
	exec InsertLop 507,'2009',N'Chính quy'
	exec InsertLop 507,'2010',N'Chính quy'
	exec InsertLop 507,'2010',N'Chính quy'
	exec InsertLop 507,'2011',N'Chính quy'
	exec InsertLop 507,'2011',N'Chính quy'
	
	exec InsertLop 509,'2007',N'Chính quy'
	exec InsertLop 509,'2007',N'Chính quy'
	exec InsertLop 509,'2008',N'Chính quy'
	exec InsertLop 509,'2008',N'Chính quy'
	exec InsertLop 509,'2009',N'Chính quy'
	exec InsertLop 509,'2009',N'Chính quy'
	exec InsertLop 509,'2010',N'Chính quy'
	exec InsertLop 509,'2010',N'Chính quy'
	exec InsertLop 509,'2011',N'Chính quy'
	exec InsertLop 509,'2011',N'Chính quy'
	
	exec InsertLop 517,'2007',N'Chính quy'
	exec InsertLop 517,'2007',N'Chính quy'
	exec InsertLop 517,'2008',N'Chính quy'
	exec InsertLop 517,'2008',N'Chính quy'
	exec InsertLop 517,'2009',N'Chính quy'
	exec InsertLop 517,'2009',N'Chính quy'
	exec InsertLop 517,'2010',N'Chính quy'
	exec InsertLop 517,'2010',N'Chính quy'
	exec InsertLop 517,'2011',N'Chính quy'
	exec InsertLop 517,'2011',N'Chính quy'
	
	exec InsertLop 505,'2007',N'Chính quy'
	exec InsertLop 505,'2007',N'Chính quy'
	exec InsertLop 505,'2008',N'Chính quy'
	exec InsertLop 505,'2008',N'Chính quy'
	exec InsertLop 505,'2009',N'Chính quy'
	exec InsertLop 505,'2009',N'Chính quy'
	exec InsertLop 505,'2010',N'Chính quy'
	exec InsertLop 505,'2010',N'Chính quy'
	exec InsertLop 505,'2011',N'Chính quy'
	exec InsertLop 505,'2011',N'Chính quy'
	
	exec InsertLop 503,'2007',N'Chính quy'
	exec InsertLop 503,'2007',N'Chính quy'
	exec InsertLop 503,'2008',N'Chính quy'
	exec InsertLop 503,'2008',N'Chính quy'
	exec InsertLop 503,'2009',N'Chính quy'
	exec InsertLop 503,'2009',N'Chính quy'
	exec InsertLop 503,'2010',N'Chính quy'
	exec InsertLop 503,'2010',N'Chính quy'
	exec InsertLop 503,'2011',N'Chính quy'
	exec InsertLop 503,'2011',N'Chính quy'
	
	SELECT * FROM Lop
*/
GO
CREATE TABLE Tinhtrang(
	matinhtrang INT PRIMARY KEY IDENTITY(1,1),
	tentinhtrang nvarchar(30)
)
GO
-- Chen du lieu vao bang Tinhtrang
INSERT INTO Tinhtrang VALUES(N'Đang học')
INSERT INTO Tinhtrang VALUES(N'Bảo lưu')
INSERT INTO Tinhtrang VALUES(N'Thôi học')
INSERT INTO Tinhtrang VALUES(N'Chấp nhận')
INSERT INTO Tinhtrang VALUES(N'Không chấp nhận')
INSERT INTO Tinhtrang VALUES(N'Chưa xử lý')

GO
CREATE TABLE Sinhvien(
	MaSV varchar(20) PRIMARY KEY,
	pass varchar(30),
	ten nvarchar(50),
	malop int,
	gioitinh nvarchar(5),
	diachi nvarchar(50),
	avatar nvarchar(200),
	matinhtrang INT,
	FOREIGN KEY (malop) REFERENCES Lop(malop) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (matinhtrang) REFERENCES Tinhtrang(matinhtrang) ON UPDATE CASCADE ON DELETE CASCADE
)
GO

-- Thu tuc chen SinhVien 
ALTER PROCEDURE InsertSinhvien
	@pass nvarchar(30),
	@ten nvarchar(50),
	@malop int,
	@gioitinh nvarchar(5),
	@diachi nvarchar(50),
	@avatar nvarchar(200)
AS
	BEGIN
		DECLARE @siso int,@so0 int,@STTMoi varchar(4),@khoahoc nvarchar(10),@makhoa int,@matruong int,@hedaotao int,@STT int,@MaSV nvarchar(20),@MaSVMoi nvarchar(20),@length int
		SELECT @khoahoc = khoahoc,@hedaotao = hedaotao FROM Lop WHERE malop = @malop
		SELECT @makhoa = makhoa FROM Lop WHERE malop = @malop
		SELECT @matruong = matruong FROM Khoa WHERE makhoa = @makhoa
		SELECT @MaSV = MaSV FROM Sinhvien WHERE malop = @malop ORDER BY MaSV 
		SELECT @siso = siso FROM Lop WHERE malop = @malop
		SET @siso = @siso + 1
		SET @khoahoc = SUBSTRING(@khoahoc,3,2) -- Lay 2 ky tu cuoi cua nam hoc
		UPDATE Lop SET siso = @siso WHERE malop = @malop
		IF(@MaSV IS NULL)
			BEGIN
				 SET @MaSVMoi = CAST(@makhoa AS nvarchar(5)) + @khoahoc + CAST(@hedaotao AS nvarchar(3)) + '001'
				 INSERT INTO Sinhvien VALUES(@MaSVMoi,@pass,@ten,@malop,@gioitinh,@diachi,@avatar,1)
			END
		ELSE
			BEGIN
				SET @length = LEN(CAST(@makhoa AS nvarchar(5)) + @khoahoc + CAST(@hedaotao AS nvarchar(3)))
				SET @STT = CAST(SUBSTRING(@MaSV,@length+1,3) AS INT)+1
				SET @so0 = 3 - LEN(@STT)
				SET @STTMoi = '0'
				-- them cac so 0 vao truoc STT
				WHILE(@so0 > 1)
					BEGIN
						SET @STTMoi = @STTMoi + '0'
						SET @so0 = @so0 - 1
					END
				-- 001 002 003
				SET @STTMoi = @STTMoi + CAST(@STT AS NVARCHAR(3))
				SET @MaSVMoi = CAST(@makhoa AS nvarchar(5)) + @khoahoc + CAST(@hedaotao AS nvarchar(3)) + @STTMoi
				INSERT INTO Sinhvien VALUES(@MaSVMoi,@pass,@ten,@malop,@gioitinh,@diachi,@avatar,1)
			END
	END

	-- Select * from Sinhvien
    /* 
		
		exec InsertSinhvien '123456' , N'Lê Viết Thắng' , 1 , N'Nam' , N'Quảng Nam' , N'/Images/Sinhvien/NoAvatarSV.jpg'
		exec InsertSinhvien '123456' , N'Lê Viết Thắng' , 1 , N'Nam' , N'Quảng Nam' , N'/Images/Sinhvien/NoAvatarSV.jpg'
		exec InsertSinhvien '123456' , N'Lê Viết Thắng' , 1 , N'Nam' , N'Quảng Nam' , N'/Images/Sinhvien/NoAvatarSV.jpg'
		exec InsertSinhvien '123456' , N'Lê Viết Thắng' , 1 , N'Nam' , N'Quảng Nam' , N'/Images/Sinhvien/NoAvatarSV.jpg'
		exec InsertSinhvien '123456' , N'Lê Viết Thắng' , 1 , N'Nam' , N'Quảng Nam' , N'/Images/Sinhvien/NoAvatarSV.jpg'
					
	*/


GO
CREATE TABLE Administrator(
	MaAdmin nvarchar(20),
	pass nvarchar(20),
	ten nvarchar(50),
	gioitinh nvarchar(5),
	diachi nvarchar(50),
	avatar nvarchar(200)
)
GO
CREATE TABLE Baoluu_Thoihoc(
	MaSV varchar(20),
	yeucau nvarchar(20),
	ngaybd datetime,
	ngaykt datetime,
	lydo nvarchar(500),
	tinhtrang int,-- Kiem tra xem yeu cau da dc xu ly chua
	FOREIGN KEY (MaSV) REFERENCES Sinhvien_Admin(MaSV) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (tinhtrang) REFERENCES Tinhtrang(matinhtrang)
)
GO
CREATE TABLE Chuyenlop(
	MaSV varchar(20),
	ngaybd datetime,
	tenlop varchar(30),
	lydo nvarchar(500),
	tinhtrang int,-- Kiem tra xem yeu cau da dc xu ly chua
	FOREIGN KEY (MaSV) REFERENCES Sinhvien_Admin(MaSV) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (tinhtrang) REFERENCES Tinhtrang(matinhtrang)
)
-- 

GO
CREATE TABLE Chuyentruong(
	MaSV varchar(20),
	ngaybd datetime,
	tentruong nvarchar(50),
	lydo nvarchar(500),
	tinhtrang int,-- Kiem tra xem yeu cau da dc xu ly chua
	FOREIGN KEY (MaSV) REFERENCES Sinhvien_Admin(MaSV)ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (tinhtrang) REFERENCES Tinhtrang(matinhtrang)
)
GO
/*
	Bang nay dung de kiem tra ket qua hoc tap cua sinh vien:
		Cac truong hop bi thoi hoc : 
			nam1 < 1.2
			nam2 < 1.4
			nam3 < 1.6
			nam4 < 1.8
			nam5 < 1.8
*/
CREATE TABLE Ketquahoctap(
	MaSV varchar(20),
	nam1 float,
	nam2 float,
	nam3 float,
	nam4 float,
	nam5 float,
	FOREIGN KEY (MaSV) REFERENCES Sinhvien_Admin(MaSV)ON UPDATE CASCADE ON DELETE CASCADE
)


-- PROCEDURE --

/*
CREATE PROCEDURE InsertLop
	@tenlop nvarchar(50),
	@hedaotao nvarchar(5),
	@makhoa nvarchar(10)
AS
	BEGIN
		DECLARE @str varchar(55)
		SET @str = @hedaotao+@makhoa
		INSERT INTO Lop(malop,tenlop) VALUES(@str,@tenlop)
	END

-- exec InsertLop 'Cong nghe thong tin','07','CNTT'

-- select * from Lop
*/
GO
-----------------------------------------------------------------------
--                         PROCEDURE Truong                          --
-----------------------------------------------------------------------
-- Thu tuc lay tat ca ten truong
CREATE PROCEDURE GetAllTruong
AS
	SELECT * FROM Truong
-- exec GetAllTruong
GO

-- Thu tuc chen ten truong vao CSDL
CREATE PROCEDURE InsertTruong
	@tentruong nvarchar(50)
AS
	INSERT INTO Truong(tentruong) VALUES(@tentruong)
	
GO

-- Thu tuc update ten truong
CREATE PROCEDURE UpdateTruong
	@matruong int,
	@tentruong nvarchar(50)
AS
	UPDATE Truong SET tentruong = @tentruong WHERE matruong=@matruong
GO

-- Thu tuc xoa truong theo ID
CREATE PROCEDURE DeleteTruongById
	@id int
AS
	DELETE FROM Truong WHERE matruong=@id
	
	-- exec DeleteTruongById 5
GO

-- Thu tuc xoa truong theo Name
CREATE PROCEDURE DeleteTruongByName
	@name nvarchar(50)
AS
	DELETE FROM Truong WHERE tentruong=@name

	-- exec DeleteTruongByName 'DHBK'
GO

-- Thu tuc tim kiem truong theo ID
CREATE PROCEDURE SearchTruongById
	@id int
AS
	SELECT matruong AS[Mã Trường], tentruong AS[Tên Trường] FROM Truong WHERE matruong=@id
	-- exec SearchTruongById 2
GO

-- Thu tuc tim kiem truong theo Name
CREATE PROCEDURE SearchTruongByName
	@name nvarchar(50)
AS
	SELECT matruong AS[Mã Trường], tentruong AS[Tên Trường] FROM Truong WHERE tentruong LIKE '%'+@name+'%'
	--exec SearchTruongByName 'Nẵng'
GO

-----------------------------------------------------------------------
--                         PROCEDURE Khoa                            --
-----------------------------------------------------------------------
-- Thu tuc lay tat ca khoa
CREATE PROCEDURE GetAllKhoa
AS
	SELECT makhoa AS [Mã Khoa],tenkhoa AS [Tên Khoa],tentruong AS[Tên Trường] FROM Khoa,Truong WHERE Khoa.matruong = Truong.matruong
-- exec GetAllKhoa
GO

-- Thu tuc chen khoa vao CSDL
CREATE PROCEDURE InsertKhoa
	@tenkhoa nvarchar(30),
	@tentruong nvarchar(50)
AS
	BEGIN
		DECLARE @matruong int
		SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		INSERT INTO Khoa(tenkhoa,matruong) VALUES(@tenkhoa,@matruong)
	END
	/* 
	 exec InsertKhoa 'Cong Nghe Moi Truong'             ,'Cao đẳng công nghệ'	
	 exec InsertKhoa 'Cong Nghe Thong Tin'              ,'Cao đẳng công nghệ'	
	 exec InsertKhoa 'Xay dung dan dung va cong nghiep' ,'Cao đẳng công nghệ'	
	 exec InsertKhoa 'Dien Tu Vien Thong'               ,'Cao đẳng công nghệ'	
	 exec InsertKhoa 'Dien Ky Thuat'                    ,'Cao đẳng công nghệ'	
	 exec InsertKhoa 'Hoa Dau'                          ,'Cao đẳng công nghệ'
	*/	
	-- SELECT * FROM Khoa
GO

-- Thu tuc update ten khoa
CREATE PROCEDURE UpdateKhoa
	@makhoa int,
	@tenkhoa nvarchar(30),
	@tentruong nvarchar(50)
AS
	BEGIN
		DECLARE @matruong INT
		SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		UPDATE Khoa SET tenkhoa=@tenkhoa , matruong = @matruong WHERE makhoa=@makhoa
	END
	-- exec UpdateKhoa 1,'Cong Nghe Moi Truong 2','Cao đẳng công nghệ'
	-- SELECT * FROM Khoa
GO

-- Thu tuc xoa khoa theo makhoa
CREATE PROCEDURE DeleteKhoaById
	@makhoa int
AS
	DELETE FROM Khoa WHERE makhoa=@makhoa
	
	-- exec DeleteKhoaById 5
GO

-- Thu tuc xoa khoa theo Name
CREATE PROCEDURE DeleteKhoaByName
	@name nvarchar(50)
AS
	DELETE FROM Khoa WHERE tenkhoa=@name

	-- exec DeleteKhoaByName 'Cong Nghe Moi Truong 2'
	-- SELECT * FROM Khoa
GO

-- Thu tuc tim kiem khoa theo ID
CREATE PROCEDURE SearchKhoaById
	@makhoa int
AS
	SELECT makhoa AS[Mã Khoa],tenkhoa AS[Tên Khoa],tentruong AS[Tên Trường] FROM Khoa,Truong 
	WHERE Khoa.matruong = Truong.matruong
	AND makhoa LIKE @makhoa
	-- exec SearchKhoaById 3
GO

-- Thu tuc tim kiem khoa theo Name
CREATE PROCEDURE SearchKhoaByName
	@name nvarchar(30)
AS
	SELECT makhoa AS[Mã Khoa],tenkhoa AS[Tên Khoa],tentruong AS[Tên Trường] FROM Khoa,Truong 
	WHERE Khoa.matruong = Truong.matruong
	AND tenkhoa LIKE '%'+@name+'%'
	--exec SearchKhoaByName 'Thong'
GO
-- Thu tuc tim kiem khoa theo ten truong
CREATE PROCEDURE SearchKhoaByTenTruong
	@tentruong nvarchar(50)
AS
	BEGIN
		--DECLARE @matruong INT
		--SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		SELECT makhoa AS[Mã Khoa],tenkhoa AS[Tên Khoa],tentruong AS[Tên Trường] FROM Khoa,Truong 
		WHERE Khoa.matruong = Truong.matruong
		AND tentruong LIKE '%'+@tentruong+'%'
	END
	--exec SearchKhoaByTenTruong 'Cao'
	-- SELECT * FROM Khoa
	-- SELECT * FROM Truong
	
GO
-----------------------------------------------------------------------
--                         PROCEDURE Lop                             --
-----------------------------------------------------------------------
-- Thu tuc lay tat ca cac lop trong CSDL
CREATE PROCEDURE GetAllLop
AS
	SELECT malop AS[Mã lớp],tenlop AS[Tên Lớp],tenkhoa AS[Tên Khoa],tentruong AS[Tên Trường],khoahoc FROM Lop,Khoa,Truong
	WHERE Lop.makhoa=Khoa.makhoa
	AND Khoa.matruong = Truong.matruong
	-- exec GetAllLop

-- Thu tuc chen 1 lop vao CSDL
CREATE PROCEDURE InsertLopBasedKhoaTruong
	@tentruong nvarchar(50),
	@tenkhoa nvarchar(50),
	@khoahoc varchar(10),
	@hedaotao nvarchar(10)
AS
	BEGIN
		DECLARE @maHDT int,@matruong int,@_khoahoc varchar(10),@makhoa int,@kyhieu varchar(5),@tenlop varchar(20),@tenlopmoi varchar(20),@so int,@sokytu int,@STT int
		SET @_khoahoc = SUBSTRING(@khoahoc,3,2)
		SELECT @maHDT = Madaotao FROM Hedaotao WHERE Tendaotao = @hedaotao
		SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		
		SELECT @makhoa = makhoa FROM Khoa WHERE tenkhoa = @tenkhoa AND matruong = @matruong
		
		SELECT @kyhieu = kyhieu FROM Khoa WHERE makhoa = @makhoa
		
		SELECT @tenlop = tenlop FROM Lop WHERE makhoa = @makhoa AND khoahoc = @khoahoc ORDER BY tenlop DESC
		IF(@tenlop is null)
			BEGIN
				SET @tenlopmoi = @_khoahoc + @kyhieu + '1'
				INSERT INTO Lop(tenlop,makhoa,khoahoc,hedaotao) VALUES(@tenlopmoi,@makhoa,@khoahoc,@maHDT)
			END
		ELSE
			BEGIN
				SET @sokytu = LEN(@_khoahoc+@kyhieu)
				-- Cat chuoi ten lop ra VD : 07T1 ta cat ra con lai 1 
				-- Sau do chuyen 1 thanh so nguyen roi + 1 la dc STT lop
				SET @STT = CAST(SUBSTRING(@tenlop,@sokytu+1,1) AS INT) + 1
				-- Thiet lap ten lop moi = 'khoahoc'+'kyhieu'+'STT'(chuyen int to nvarchar)
				SET @tenlopmoi = @_khoahoc + @kyhieu + CAST(@STT AS NVARCHAR(3))
				INSERT INTO Lop(tenlop,makhoa,khoahoc,hedaotao) VALUES(@tenlopmoi,@makhoa,@khoahoc,@maHDT)
			END
	END
	/*
	 exec InsertLopBasedKhoaTruong N'Đại học Bách Khoa Đà Nẵng',N'Công Nghệ Thông Tin','2007',N'Chính quy'
	 SELECT * FROM Lop
	 */
	
GO
-- Thu tuc cap nhat thong tin cho lop
CREATE PROCEDURE UpdateLop
	@malop int,
	@tenlop varchar(10),
	@tenkhoa varchar(50),
	@khoahoc varchar(50)
AS
	BEGIN
		DECLARE @makhoa INT
		SELECT @makhoa = makhoa FROM Khoa WHERE tenkhoa = @tenkhoa
		UPDATE Lop SET tenlop = @tenlop , makhoa = @makhoa , khoahoc = @khoahoc WHERE malop = @malop
	END
	
	-- exec UpdateLop 9,'07T1','Cong Nghe Thong Tin','2007-2012'
	-- select * from Lop
GO
-- Thu tuc xoa Lop theo Malop
CREATE PROCEDURE

GO
-- Thu tuc xoa Lop theo TenLop
CREATE PROCEDURE

GO
-- Thu tuc tim kiem Lop theo Malop
CREATE PROCEDURE

GO
-- Thu tuc tim kiem Lop theo TenLop
CREATE PROCEDURE

GO
-- Thu tuc tim kiem Lop theo Khoa
CREATE PROCEDURE

GO
-- Thu tuc tim kiem Lop theo Truong,Khoa


GO
-----------------------------------------------------------------------
--                         PROCEDURE Sinhvien                        --
-----------------------------------------------------------------------
GO
-----------------------------------------------------------------------
--                         PROCEDURE Admin                           --
-----------------------------------------------------------------------
GO
-----------------------------------------------------------------------
--                     PROCEDURE Baoluu_Thoihoc                      --
-----------------------------------------------------------------------
GO
-----------------------------------------------------------------------
--                         PROCEDURE ChuyenLop                       --
-----------------------------------------------------------------------
GO
-----------------------------------------------------------------------
--                         PROCEDURE Chuyentruong                    --
-----------------------------------------------------------------------
GO
-----------------------------------------------------------------------
--                         PROCEDURE Ketquahoctap                    --
-----------------------------------------------------------------------
