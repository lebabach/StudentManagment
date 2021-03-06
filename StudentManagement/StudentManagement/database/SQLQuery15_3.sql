CREATE DATABASE StudentManagement
GO
-- drop database StudentManagement
USE StudentManagement
GO
CREATE TABLE Truong(
	matruong INT PRIMARY KEY IDENTITY(1,1),
	tentruong nvarchar(50)
)
GO
-- Chen du lieu vao bang Truong
INSERT INTO Truong VALUES(N'Đại học Bách Khoa Đà Nẵng')
INSERT INTO Truong VALUES(N'Đại học Kinh Tế Đà Nẵng')
INSERT INTO Truong VALUES(N'Đại học Sư Phạm Đà Nẵng')
INSERT INTO Truong VALUES(N'Cao đẳng công nghệ')
INSERT INTO Truong VALUES(N'Cao đẳng Kinh Tế Kế Hoạch')
GO
CREATE TABLE Khoa(
	makhoa int PRIMARY KEY,
	tenkhoa nvarchar(50),
	matruong int,
	kyhieu nvarchar(5),
	FOREIGN KEY(matruong) REFERENCES Truong(matruong) ON UPDATE CASCADE ON DELETE CASCADE
)
GO
-- select * from Quyetdinh

-- Thu tuc chen du lieu vao Khoa
CREATE PROCEDURE InsertKhoa
	@tenkhoa nvarchar(50),
	@matruong int,
	@kyhieu nvarchar(5)
AS
	BEGIN
		DECLARE @makhoa int,@chuoi nvarchar(4)
		SELECT @makhoa = makhoa FROM Khoa WHERE matruong = @matruong ORDER BY makhoa
		IF(@makhoa IS NULL)
			BEGIN
				SET @chuoi = CAST(@matruong AS NVARCHAR(3)) + '01'
				SET @makhoa = CAST(@chuoi AS INT)
				INSERT INTO Khoa VALUES(@makhoa,@tenkhoa,@matruong,@kyhieu)
			END
		ELSE
			BEGIN
				SET @makhoa = @makhoa + 1
				INSERT INTO Khoa VALUES(@makhoa,@tenkhoa,@matruong,@kyhieu)
			END
	END
GO
-- Ma khoa phai nhap bang tay vi khoa la co dinh khong the thay bag ma so khac vi vay moi khoa co 1 ma so dc cap rieng tuong ung voi truong hien tai
-- Chen du lieu vao bang Khoa
	-- Khoa cua truong DHBK
	exec InsertKhoa N'Cơ Khí Chế Tạo',1,'C'
	exec InsertKhoa N'Điện Kỹ Thuật',1,'D'
	exec InsertKhoa N'Điện Tử Viễn Thông',1,'DT'
	exec InsertKhoa N'Xây Dựng Dân Dụng và Công Nghiệp',1,'XD'
	exec InsertKhoa N'Công Nghệ Thông Tin',1,'T'
	-- Khoa cua truong DHKT
	exec InsertKhoa N'Khoa Kinh Tế',2,'K'
	exec InsertKhoa N'Khoa Mac-Lênin',2,'ML'
	exec InsertKhoa N'Khoa Tài Chính - Ngân Hàng',2,'TC'
	exec InsertKhoa N'Khoa Kế Toán',2,'KT'
	exec InsertKhoa N'Khoa Quản Trị Kinh Doanh',2,'QT'
	-- Khoa cua truong DHSP
	exec InsertKhoa N'Khoa Toán',3,'ST'
	exec InsertKhoa N'Khoa Lý',3,'SVL'
	exec InsertKhoa N'Khoa Hóa',3,'SHH'
	exec InsertKhoa N'Khoa Sinh',3,'SSS'
	exec InsertKhoa N'Khoa Tin',3,'SPT'
	-- Khoa cua truong CDCN
	exec InsertKhoa N'Cơ Khí Chế Tạo',4,'C'
	exec InsertKhoa N'Điện Kỹ Thuật',4,'D'
	exec InsertKhoa N'Điện Tử Viễn Thông',4,'DT'
	exec InsertKhoa N'Xây Dựng Dân Dụng và Công Nghiệp',4,'XD'
	exec InsertKhoa N'Công Nghệ Thông Tin',4,'T'
	-- Khoa cua truong CDKTKH
	exec InsertKhoa N'Khoa Kinh Tế',5,'K'
	exec InsertKhoa N'Khoa Mac-Lênin',5,'ML'
	exec InsertKhoa N'Khoa Tài Chính - Ngân Hàng',5,'TC'
	exec InsertKhoa N'Khoa Kế Toán',5,'KT'
	exec InsertKhoa N'Khoa Quản Trị Kinh Doanh',5,'QT'
	-- Select * from Khoa
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
GO
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
	
	exec InsertLop 105,'2007',N'Chính quy'
	exec InsertLop 105,'2007',N'Chính quy'
	exec InsertLop 105,'2008',N'Chính quy'
	exec InsertLop 105,'2008',N'Chính quy'
	exec InsertLop 105,'2009',N'Chính quy'
	exec InsertLop 105,'2009',N'Chính quy'
	exec InsertLop 105,'2010',N'Chính quy'
	exec InsertLop 105,'2010',N'Chính quy'
	exec InsertLop 105,'2011',N'Chính quy'
	exec InsertLop 105,'2011',N'Chính quy'
	
	SELECT * FROM Lop

-- Chen LOP cho truong Dai Hoc Kinh Te

	exec InsertLop 201,'2007',N'Chính quy'
	exec InsertLop 201,'2007',N'Chính quy'
	exec InsertLop 201,'2008',N'Chính quy'
	exec InsertLop 201,'2008',N'Chính quy'
	exec InsertLop 201,'2009',N'Chính quy'
	exec InsertLop 201,'2009',N'Chính quy'
	exec InsertLop 201,'2010',N'Chính quy'
	exec InsertLop 201,'2010',N'Chính quy'
	exec InsertLop 201,'2011',N'Chính quy'
	exec InsertLop 201,'2011',N'Chính quy'
	
	exec InsertLop 202,'2007',N'Chính quy'
	exec InsertLop 202,'2007',N'Chính quy'
	exec InsertLop 202,'2008',N'Chính quy'
	exec InsertLop 202,'2008',N'Chính quy'
	exec InsertLop 202,'2009',N'Chính quy'
	exec InsertLop 202,'2009',N'Chính quy'
	exec InsertLop 202,'2010',N'Chính quy'
	exec InsertLop 202,'2010',N'Chính quy'
	exec InsertLop 202,'2011',N'Chính quy'
	exec InsertLop 202,'2011',N'Chính quy'
	
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
	
	exec InsertLop 204,'2007',N'Chính quy'
	exec InsertLop 204,'2007',N'Chính quy'
	exec InsertLop 204,'2008',N'Chính quy'
	exec InsertLop 204,'2008',N'Chính quy'
	exec InsertLop 204,'2009',N'Chính quy'
	exec InsertLop 204,'2009',N'Chính quy'
	exec InsertLop 204,'2010',N'Chính quy'
	exec InsertLop 204,'2010',N'Chính quy'
	exec InsertLop 204,'2011',N'Chính quy'
	exec InsertLop 204,'2011',N'Chính quy'
	
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
	
	SELECT * FROM Lop


-- Chen LOP cho truong Dai Hoc Su Pham

	exec InsertLop 301,'2007',N'Chính quy'
	exec InsertLop 301,'2007',N'Chính quy'
	exec InsertLop 301,'2008',N'Chính quy'
	exec InsertLop 301,'2008',N'Chính quy'
	exec InsertLop 301,'2009',N'Chính quy'
	exec InsertLop 301,'2009',N'Chính quy'
	exec InsertLop 301,'2010',N'Chính quy'
	exec InsertLop 301,'2010',N'Chính quy'
	exec InsertLop 301,'2011',N'Chính quy'
	exec InsertLop 301,'2011',N'Chính quy'
	
	exec InsertLop 302,'2007',N'Chính quy'
	exec InsertLop 302,'2007',N'Chính quy'
	exec InsertLop 302,'2008',N'Chính quy'
	exec InsertLop 302,'2008',N'Chính quy'
	exec InsertLop 302,'2009',N'Chính quy'
	exec InsertLop 302,'2009',N'Chính quy'
	exec InsertLop 302,'2010',N'Chính quy'
	exec InsertLop 302,'2010',N'Chính quy'
	exec InsertLop 302,'2011',N'Chính quy'
	exec InsertLop 302,'2011',N'Chính quy'
	
	exec InsertLop 303,'2007',N'Chính quy'
	exec InsertLop 303,'2007',N'Chính quy'
	exec InsertLop 303,'2008',N'Chính quy'
	exec InsertLop 303,'2008',N'Chính quy'
	exec InsertLop 303,'2009',N'Chính quy'
	exec InsertLop 303,'2009',N'Chính quy'
	exec InsertLop 303,'2010',N'Chính quy'
	exec InsertLop 303,'2010',N'Chính quy'
	exec InsertLop 303,'2011',N'Chính quy'
	exec InsertLop 303,'2011',N'Chính quy'
	
	exec InsertLop 304,'2007',N'Chính quy'
	exec InsertLop 304,'2007',N'Chính quy'
	exec InsertLop 304,'2008',N'Chính quy'
	exec InsertLop 304,'2008',N'Chính quy'
	exec InsertLop 304,'2009',N'Chính quy'
	exec InsertLop 304,'2009',N'Chính quy'
	exec InsertLop 304,'2010',N'Chính quy'
	exec InsertLop 304,'2010',N'Chính quy'
	exec InsertLop 304,'2011',N'Chính quy'
	exec InsertLop 304,'2011',N'Chính quy'

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
	
	SELECT * FROM Lop


-- Chen LOP cho truong Cao Dang Cong Nghe

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
	
	exec InsertLop 405,'2007',N'Chính quy'
	exec InsertLop 405,'2007',N'Chính quy'
	exec InsertLop 405,'2008',N'Chính quy'
	exec InsertLop 405,'2008',N'Chính quy'
	exec InsertLop 405,'2009',N'Chính quy'
	exec InsertLop 405,'2009',N'Chính quy'
	exec InsertLop 405,'2010',N'Chính quy'
	exec InsertLop 405,'2010',N'Chính quy'
	exec InsertLop 405,'2011',N'Chính quy'
	exec InsertLop 405,'2011',N'Chính quy'
	
	SELECT * FROM Lop


-- Chen LOP cho truong Cao Dang Kinh Te Ke Hoach

	exec InsertLop 501,'2007',N'Chính quy'
	exec InsertLop 501,'2007',N'Chính quy'
	exec InsertLop 501,'2008',N'Chính quy'
	exec InsertLop 501,'2008',N'Chính quy'
	exec InsertLop 501,'2009',N'Chính quy'
	exec InsertLop 501,'2009',N'Chính quy'
	exec InsertLop 501,'2010',N'Chính quy'
	exec InsertLop 501,'2010',N'Chính quy'
	exec InsertLop 501,'2011',N'Chính quy'
	exec InsertLop 501,'2011',N'Chính quy'
	
	exec InsertLop 502,'2007',N'Chính quy'
	exec InsertLop 502,'2007',N'Chính quy'
	exec InsertLop 502,'2008',N'Chính quy'
	exec InsertLop 502,'2008',N'Chính quy'
	exec InsertLop 502,'2009',N'Chính quy'
	exec InsertLop 502,'2009',N'Chính quy'
	exec InsertLop 502,'2010',N'Chính quy'
	exec InsertLop 502,'2010',N'Chính quy'
	exec InsertLop 502,'2011',N'Chính quy'
	exec InsertLop 502,'2011',N'Chính quy'
	
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
	
	exec InsertLop 504,'2007',N'Chính quy'
	exec InsertLop 504,'2007',N'Chính quy'
	exec InsertLop 504,'2008',N'Chính quy'
	exec InsertLop 504,'2008',N'Chính quy'
	exec InsertLop 504,'2009',N'Chính quy'
	exec InsertLop 504,'2009',N'Chính quy'
	exec InsertLop 504,'2010',N'Chính quy'
	exec InsertLop 504,'2010',N'Chính quy'
	exec InsertLop 504,'2011',N'Chính quy'
	exec InsertLop 504,'2011',N'Chính quy'
	
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
	
	SELECT * FROM Lop

GO
CREATE TABLE Tinhtrang(
	matinhtrang INT PRIMARY KEY,
	tentinhtrang nvarchar(30)
)
GO
-- Chen du lieu vao bang Tinhtrang
INSERT INTO Tinhtrang VALUES(1,N'Đang học')
INSERT INTO Tinhtrang VALUES(2,N'Bảo lưu')
INSERT INTO Tinhtrang VALUES(3,N'Thôi học')
INSERT INTO Tinhtrang VALUES(4,N'Chấp nhận')
INSERT INTO Tinhtrang VALUES(5,N'Không chấp nhận')
INSERT INTO Tinhtrang VALUES(6,N'Chưa xử lý')

GO
CREATE TABLE Sinhvien(
	MaSV varchar(20) PRIMARY KEY,
	pass varchar(30),
	ten nvarchar(50),
	email nvarchar(30),
	malop int,
	gioitinh nvarchar(5),
	diachi nvarchar(50),
	avatar nvarchar(200) DEFAULT(N'/Images/Sinhvien/NoAvatarSV.jpg'),
	matinhtrang INT,
	FOREIGN KEY (malop) REFERENCES Lop(malop) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (matinhtrang) REFERENCES Tinhtrang(matinhtrang) ON UPDATE CASCADE ON DELETE CASCADE
)
GO

-- Thu tuc chen SinhVien 
CREATE PROCEDURE InsertSinhvien
	@pass nvarchar(30),
	@ten nvarchar(50),
	@malop int,
	@gioitinh nvarchar(5),
	@email nvarchar(30),
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
				 INSERT INTO Sinhvien VALUES(@MaSVMoi,@pass,@ten,@email,@malop,@gioitinh,@diachi,@avatar,1)
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
				INSERT INTO Sinhvien VALUES(@MaSVMoi,@pass,@ten,@email,@malop,@gioitinh,@diachi,@avatar,1)
			END
	END
GO
	-- Select * from Sinhvien		
	exec InsertSinhvien '123456' , N'Lê Viết Thắng' , 1 , N'Nam' ,'thang@yahoo.com', N'Quảng Nam' , N'/Images/Sinhvien/NoAvatarSV.jpg'
	exec InsertSinhvien '123456' , N'Lê Viết Thắng' , 1 , N'Nam' ,'thang123@yahoo.com', N'Quảng Nam' , N'/Images/Sinhvien/NoAvatarSV.jpg'
	exec InsertSinhvien '123456' , N'Lê Viết Thắng' , 1 , N'Nam' ,'thang1234@yahoo.com', N'Quảng Nam' , N'/Images/Sinhvien/NoAvatarSV.jpg'
	exec InsertSinhvien '123456' , N'Lê Viết Thắng' , 1 , N'Nam' ,'vietthang@yahoo.com', N'Quảng Nam' , N'/Images/Sinhvien/NoAvatarSV.jpg'
	exec InsertSinhvien '123456' , N'Lê Viết Thắng' , 1 , N'Nam' ,'lethang@yahoo.com', N'Quảng Nam' , N'/Images/Sinhvien/NoAvatarSV.jpg'
					
GO
CREATE TABLE Administrator(
	MaAdmin nvarchar(20),
	pass nvarchar(20),
	ten nvarchar(50),
	gioitinh nvarchar(5),
	email nvarchar(30),
	diachi nvarchar(50),
	avatar nvarchar(200)
)
GO
-- Thu tuc chen vao 1 Administrator
CREATE PROCEDURE InsertAdmin
	@pass nvarchar(20),
	@ten nvarchar(50),
	@gioitinh nvarchar(5),
	@email nvarchar(30),
	@diachi nvarchar(50),
	@avatar nvarchar(200)
AS
	BEGIN
		DECLARE @MaAdmin nvarchar(20),@so0 int,@STT int,@themso0 nvarchar(4)
		SELECT @MaAdmin = MaAdmin FROM Administrator ORDER BY MaAdmin
		IF(@MaAdmin IS NULL)
			BEGIN
				SET @MaAdmin = 'Administrator001'
				INSERT INTO Administrator VALUES(@MaAdmin,@pass,@ten,@gioitinh,@email,@diachi,@avatar)
			END
		ELSE 
			BEGIN
				SET @so0 = LEN('Administrator')
				SET @STT = CAST(SUBSTRING(@MaAdmin,@so0+1,3) AS INT) + 1
				SET @themso0 = '0'
				SET @so0 = 3-LEN(@STT)
				WHILE(@so0 > 1)
					BEGIN
						SET @themso0 = @themso0 + '0'
						SET @so0 = @so0 - 1
					END
				SET @MaAdmin = 'Administrator' + @themso0 + CAST(@STT AS NVARCHAR(4))
				INSERT INTO Administrator VALUES(@MaAdmin,@pass,@ten,@gioitinh,@email,@diachi,@avatar)
			END
	END

GO
--	Chèn Administrator vào CSDL

	exec InsertAdmin '123456',N'Lê Viết Thắng',N'Nam','admin@gmail.com',N'Quảng Nam',N'/Images/Admin/NoAvatarAdmin.jpg'
	exec InsertAdmin '123456',N'Lê Viết Thắng',N'Nam','admin@gmail.com',N'Quảng Nam',N'/Images/Admin/NoAvatarAdmin.jpg'
	exec InsertAdmin '123456',N'Lê Viết Thắng',N'Nam','admin@gmail.com',N'Quảng Nam',N'/Images/Admin/NoAvatarAdmin.jpg'
	exec InsertAdmin '123456',N'Lê Viết Thắng',N'Nam','admin@gmail.com',N'Quảng Nam',N'/Images/Admin/NoAvatarAdmin.jpg'

-- SELECT * FROM Administrator
GO
CREATE TABLE Yeucau(
	mayeucau int PRIMARY KEY,
	tenyeucau nvarchar(20)
)
GO
-- Chen du lieu vao bang Yeucau
INSERT INTO Yeucau(mayeucau,tenyeucau) VALUES (1,N'Thôi Học')
INSERT INTO Yeucau(mayeucau,tenyeucau) VALUES (2,N'Bảo Lưu')
INSERT INTO Yeucau(mayeucau,tenyeucau) VALUES (3,N'Chuyển Lớp')
INSERT INTO Yeucau(mayeucau,tenyeucau) VALUES (4,N'Chuyển Trường')
INSERT INTO Yeucau(mayeucau,tenyeucau) VALUES (5,N'Phúc Khảo')
INSERT INTO Yeucau(mayeucau,tenyeucau) VALUES (6,N'Hiệu Trưởng')

GO 
CREATE TABLE Quyetdinh(
	MaSV varchar(20),
	ngaybd datetime DEFAULT(NULL),
	ngaykt datetime DEFAULT(NULL),
	mayeucau int,
	matruong int,
	makhoa int,
	malop int,
	maMH int DEFAULT(NULL),
	lydo nvarchar(500) DEFAULT(NULL),
	tinhtrang int DEFAULT(6),-- Kiem tra xem yeu cau da dc xu ly chua
	FOREIGN KEY (MaSV) REFERENCES Sinhvien(MaSV)ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (matruong) REFERENCES Truong(matruong),
	FOREIGN KEY (makhoa) REFERENCES Khoa(makhoa),
	FOREIGN KEY (malop) REFERENCES Lop(malop),
	FOREIGN KEY (tinhtrang) REFERENCES Tinhtrang(matinhtrang),
	FOREIGN KEY (mayeucau) REFERENCES Yeucau(mayeucau),
	FOREIGN KEY (maMH) REFERENCES Monhoc(MaMH)
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
/*
	QUAN LI DIEM
*/
GO
CREATE TABLE Hocky(
	maHK nvarchar(4) PRIMARY KEY NOT NULL,
	tenHK nvarchar(10)
)
GO
-- Chen du lieu vao bang Hocky
INSERT INTO Hocky VALUES('HK1' ,N'Học Kỳ 1')
INSERT INTO Hocky VALUES('HK2' ,N'Học Kỳ 2')
INSERT INTO Hocky VALUES('HK3' ,N'Học Kỳ 3')
INSERT INTO Hocky VALUES('HK4' ,N'Học Kỳ 4')
INSERT INTO Hocky VALUES('HK5' ,N'Học Kỳ 5')
INSERT INTO Hocky VALUES('HK6' ,N'Học Kỳ 6')
INSERT INTO Hocky VALUES('HK7' ,N'Học Kỳ 7')
INSERT INTO Hocky VALUES('HK8' ,N'Học Kỳ 8')
INSERT INTO Hocky VALUES('HK9' ,N'Học Kỳ 9')
INSERT INTO Hocky VALUES('HK91',N'Học Kỳ 10')

GO
CREATE TABLE Monhoc(
	MaMH int PRIMARY KEY NOT NULL IDENTITY(1,1),
	tenMH nvarchar(30),
	soTC int,
	maHK nvarchar(4),
	makhoa int,
	FOREIGN KEY(maHK) REFERENCES Hocky(maHK),
	FOREIGN KEY(makhoa) REFERENCES Khoa(makhoa) ON UPDATE CASCADE ON DELETE CASCADE
)

GO
-- Nhap diem voi dieu kien sinh vien o tinh trang dang hoc
CREATE TABLE Diem(
	MaSV varchar(20),
	MaMH int,
	DiemBT float,
	DiemGK float,
	DiemCK float,
	FOREIGN KEY (MaSV) REFERENCES Sinhvien(MaSV),
	FOREIGN KEY (MaMH) REFERENCES Monhoc(MaMH) ON UPDATE CASCADE ON DELETE CASCADE
)

/***********************************************************************/
--                            PROCEDURE                                --
/***********************************************************************/

GO
-----------------------------------------------------------------------
--                         PROCEDURE Truong                          --
-----------------------------------------------------------------------
-- Thu tuc lay tat ca ten truong
CREATE PROCEDURE GetAllTruong
AS
	SELECT matruong AS[Mã Trường],tentruong AS [Tên Trường] FROM Truong
-- exec GetAllTruong
GO

-- Thu tuc chen ten truong vao CSDL
CREATE PROCEDURE InsertTruong
	@tentruong nvarchar(50)
AS
	INSERT INTO Truong VALUES(@tentruong)
	-- exec InsertTruong N'Đại học Thể Dục Thể Thao'
	-- select * from Truong
GO

-- Thu tuc update ten truong
CREATE PROCEDURE UpdateTruong
	@matruong int,
	@tentruong nvarchar(50)
AS
	UPDATE Truong SET tentruong = @tentruong WHERE matruong=@matruong
	-- exec UpdateTruong 6,N'Cao Đẳng Công Nghệ Thông Tin'
	-- Select * from Truong
GO
-- Thu tuc xoa truong theo ID
CREATE PROCEDURE DeleteTruongById
	@id int
AS
	DELETE FROM Truong WHERE matruong=@id
	
	-- exec DeleteTruongById 6
GO

-- Thu tuc xoa truong theo Name
CREATE PROCEDURE DeleteTruongByName
	@name nvarchar(50)
AS
	DELETE FROM Truong WHERE tentruong LIKE @name
	-- select * from Truong
	-- exec DeleteTruongByName N'Đại học Thể Dục Thể Thao'
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
	--exec SearchTruongByName N'Nẵng'

GO

-----------------------------------------------------------------------
--                         PROCEDURE Khoa                            --
-----------------------------------------------------------------------
-- Thu tuc lay tat ca khoa
CREATE PROCEDURE GetAllKhoa
AS
	SELECT makhoa AS [Mã Khoa],tenkhoa AS [Tên Khoa],tentruong AS[Tên Trường],kyhieu AS[Ký Hiệu] FROM Khoa,Truong WHERE Khoa.matruong = Truong.matruong
-- exec GetAllKhoa
GO

-- Thu tuc chen khoa vao CSDL

CREATE PROCEDURE InsertKhoaByTruong
	@tenkhoa nvarchar(50),
	@tentruong nvarchar(30),
	@kyhieu nvarchar(5)
AS
	BEGIN
		DECLARE @matruong int,@makhoa int,@chuoi nvarchar(4)
		SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		SELECT @makhoa = makhoa FROM Khoa WHERE matruong = @matruong ORDER BY makhoa
		IF(@makhoa IS NULL)
			BEGIN
				SET @chuoi = CAST(@matruong AS NVARCHAR(3)) + '01'
				SET @makhoa = CAST(@chuoi AS INT)
				INSERT INTO Khoa VALUES(@makhoa,@tenkhoa,@matruong,@kyhieu)
			END
		ELSE
			BEGIN
				SET @makhoa = @makhoa + 1
				INSERT INTO Khoa VALUES(@makhoa,@tenkhoa,@matruong,@kyhieu)
			END
	END

	/* 
	 exec InsertKhoaByTruong N'Công Nghệ Môi Trường' ,N'Đại học Bách Khoa Đà Nẵng','MT'	
	 exec InsertKhoaByTruong N'Công Nghệ Môi Trường' ,N'Đại học Bách Khoa Đà Nẵng','MT'	
	 exec InsertKhoaByTruong N'Công Nghệ Môi Trường' ,N'Đại học Bách Khoa Đà Nẵng','MT'	
	 SELECT * FROM Khoa
	*/	
GO

-- Thu tuc update ten khoa
CREATE PROCEDURE UpdateKhoa
	@makhoa int,
	@tenkhoa nvarchar(30),
	@tentruong nvarchar(50),
	@kyhieu nvarchar(5)
AS
	BEGIN
		DECLARE @matruong INT
		SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		UPDATE Khoa SET tenkhoa=@tenkhoa , matruong = @matruong , kyhieu = @kyhieu WHERE makhoa=@makhoa
	END
	-- exec UpdateKhoa 106,N'Công Nghệ Môi Trường 2' ,N'Đại học Bách Khoa Đà Nẵng','MT1'
	-- SELECT * FROM Khoa
GO

-- Thu tuc xoa khoa theo makhoa
CREATE PROCEDURE DeleteKhoaById
	@makhoa int
AS
	DELETE FROM Khoa WHERE makhoa=@makhoa
	
	-- exec DeleteKhoaById 107
GO

-- Thu tuc xoa khoa theo Name
CREATE PROCEDURE DeleteKhoaByName
	@name nvarchar(50)
AS
	DELETE FROM Khoa WHERE tenkhoa LIKE @name

	-- exec DeleteKhoaByName N'Công Nghệ Môi Trường 2'
	-- SELECT * FROM Khoa
GO

-- Thu tuc tim kiem khoa theo ID
CREATE PROCEDURE SearchKhoaById
	@makhoa int
AS
	SELECT makhoa AS[Mã Khoa],tenkhoa AS[Tên Khoa],tentruong AS[Tên Trường],kyhieu AS[Ký Hiệu] FROM Khoa,Truong 
	WHERE Khoa.matruong = Truong.matruong
	AND makhoa LIKE @makhoa
	-- exec SearchKhoaById 101
GO

-- Thu tuc tim kiem khoa theo Name
CREATE PROCEDURE SearchKhoaByName
	@name nvarchar(30)
AS
	SELECT makhoa AS[Mã Khoa],tenkhoa AS[Tên Khoa],tentruong AS[Tên Trường],kyhieu AS[Ký Hiệu] FROM Khoa,Truong 
	WHERE Khoa.matruong = Truong.matruong
	AND tenkhoa LIKE '%'+@name+'%'
	-- exec SearchKhoaByName N'Thông'
GO
-- Thu tuc tim kiem khoa theo ten truong
CREATE PROCEDURE SearchKhoaByTenTruong
	@tentruong nvarchar(50)
AS
	BEGIN
		--DECLARE @matruong INT
		--SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		SELECT makhoa AS[Mã Khoa],tenkhoa AS[Tên Khoa],tentruong AS[Tên Trường] ,kyhieu AS[Ký Hiệu] FROM Khoa,Truong 
		WHERE Khoa.matruong = Truong.matruong
		AND tentruong LIKE '%'+@tentruong+'%'
	END
	-- exec SearchKhoaByTenTruong 'Cao'
	-- SELECT * FROM Khoa
	-- SELECT * FROM Truong
	
GO
-----------------------------------------------------------------------
--                         PROCEDURE Lop                             --
-----------------------------------------------------------------------
-- Thu tuc lay tat ca cac lop trong CSDL
CREATE PROCEDURE GetAllLop
AS
	SELECT malop AS[Mã lớp],tenlop AS[Tên Lớp],tenkhoa AS[Tên Khoa],tentruong AS[Tên Trường],khoahoc AS [Năm Học] ,Tendaotao AS [Hệ Đào Tạo],siso AS[Sỉ Số]  FROM Lop,Khoa,Truong,Hedaotao
	WHERE Lop.makhoa  = Khoa.makhoa
	AND Khoa.matruong = Truong.matruong
	AND Lop.hedaotao  = Hedaotao.Madaotao
	ORDER BY khoahoc
	-- exec GetAllLop
GO
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
		SELECT @tenlop = tenlop FROM Lop WHERE makhoa = @makhoa AND khoahoc = @khoahoc ORDER BY tenlop
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
	 SELECT * FROM Lop WHERE makhoa = 105
	 */
	
GO
-- Thu tuc xoa Lop theo Malop
CREATE PROCEDURE DeleteLopByID
	@malop int
AS
	DELETE FROM Lop WHERE malop = @malop
	-- exec DeleteLopByID 261
	-- select * from Lop
GO
-- Thu tuc xoa Lop theo TenLop
CREATE PROCEDURE DeleteLopByName
	@tenlop nvarchar(30)
AS
	DELETE FROM Lop WHERE tenlop LIKE @tenlop
	-- exec DeleteLopByName N'07T3'
GO
-- Thu tuc tim kiem Lop theo Malop
CREATE PROCEDURE SearchLopByID
	@malop int
AS
	SELECT malop AS[Mã lớp],tenlop AS[Tên Lớp],tenkhoa AS[Tên Khoa],tentruong AS[Tên Trường],khoahoc AS [Năm Học] ,Tendaotao AS [Hệ Đào Tạo],siso AS[Sỉ Số]  FROM Lop,Khoa,Truong,Hedaotao
	WHERE Lop.makhoa  = Khoa.makhoa
	AND Khoa.matruong = Truong.matruong
	AND Lop.hedaotao  = Hedaotao.Madaotao
	AND malop = @malop
	-- exec SearchLopByID 101

GO
-- Thu tuc tim kiem Lop theo TenLop
CREATE PROCEDURE SearchLopByName
	@tenlop nvarchar(20)
AS
	SELECT malop AS[Mã lớp],tenlop AS[Tên Lớp],tenkhoa AS[Tên Khoa],tentruong AS[Tên Trường],khoahoc AS [Năm Học] ,Tendaotao AS [Hệ Đào Tạo],siso AS[Sỉ Số]  FROM Lop,Khoa,Truong,Hedaotao
	WHERE Lop.makhoa  = Khoa.makhoa
	AND Khoa.matruong = Truong.matruong
	AND Lop.hedaotao  = Hedaotao.Madaotao
	AND tenlop LIKE '%'+@tenlop+'%'
	-- exec SearchLopByName '07T1'
GO
-- Thu tuc tim kiem Lop theo Khoa
CREATE PROCEDURE SearchLopByKhoa
	@tenkhoa nvarchar(50)
AS
	BEGIN
		SELECT malop AS[Mã lớp],tenlop AS[Tên Lớp],tenkhoa AS[Tên Khoa],tentruong AS[Tên Trường],khoahoc AS [Năm Học] ,Tendaotao AS [Hệ Đào Tạo] ,siso AS[Sỉ Số] FROM Lop,Khoa,Truong,Hedaotao
		WHERE Lop.makhoa  = Khoa.makhoa
		AND Khoa.matruong = Truong.matruong
		AND Lop.hedaotao  = Hedaotao.Madaotao
		AND Khoa.tenkhoa LIKE '%'+@tenkhoa+'%'
		ORDER BY khoahoc
	END
	
	-- exec SearchLopByKhoa N'Công Nghệ Thông Tin'

GO
-- Thu tuc tim kiem Lop theo Truong,Khoa
CREATE PROCEDURE SearchLopByTruongKhoa
	@tentruong nvarchar(50),
	@tenkhoa nvarchar(50)
AS
	BEGIN
		DECLARE @matruong int,@makhoa int
		SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		SELECT @makhoa = makhoa FROM Khoa WHERE tenkhoa = @tenkhoa AND matruong = @matruong
		SELECT malop AS[Mã lớp],tenlop AS[Tên Lớp],tenkhoa AS[Tên Khoa],tentruong AS[Tên Trường],khoahoc AS [Năm Học] ,Tendaotao AS [Hệ Đào Tạo],siso AS[Sỉ Số]  FROM Lop,Khoa,Truong,Hedaotao
		WHERE Lop.makhoa  = Khoa.makhoa
		AND Khoa.matruong = Truong.matruong
		AND Lop.hedaotao  = Hedaotao.Madaotao
		AND Khoa.tenkhoa LIKE '%'+@tenkhoa+'%'
		-- AND Khoa.matruong = @matruong
		ORDER BY khoahoc
	END
	-- exec SearchLopByTruongKhoa N'Đại học Bách Khoa Đà Nẵng',N'Cơ Khí Chế Tạo'
	-- Select * from Khoa
GO
-- Thu tuc tim kiem Lop theo Truong,Khoa
CREATE PROCEDURE SearchLopByTruongKhoaLop
	@tentruong nvarchar(50),
	@tenkhoa nvarchar(50),
	@tenlop nvarchar(50)
AS
	BEGIN
		DECLARE @matruong int,@makhoa int,@khoahoc nvarchar(10)
		SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		SELECT @makhoa = makhoa FROM Khoa WHERE tenkhoa = @tenkhoa AND matruong = @matruong
		SELECT @khoahoc = khoahoc FROM Lop WHERE makhoa = @makhoa AND tenlop = @tenlop
		SELECT malop AS[Mã lớp],tenlop AS[Tên Lớp],tenkhoa AS[Tên Khoa],tentruong AS[Tên Trường],khoahoc AS [Năm Học] ,Tendaotao AS [Hệ Đào Tạo],siso AS[Sỉ Số]  FROM Lop,Khoa,Truong,Hedaotao
		WHERE Lop.makhoa  = Khoa.makhoa
		AND Khoa.matruong = Truong.matruong
		AND Lop.hedaotao  = Hedaotao.Madaotao
		AND Lop.khoahoc = @khoahoc
		AND Khoa.tenkhoa LIKE '%'+@tenkhoa+'%'
		AND Khoa.matruong = @matruong
		ORDER BY khoahoc
	END
	-- exec SearchLopByTruongKhoaLop N'Đại học Bách Khoa Đà Nẵng',N'Cơ Khí Chế Tạo','07C1'
	-- Select * from Khoa
GO
-----------------------------------------------------------------------
--                         PROCEDURE Sinhvien                        --
-----------------------------------------------------------------------
-- Thu tuc lay tat ca sinh vien trong CSDL
CREATE PROCEDURE GetAllSinhvien
AS
	SELECT MaSV AS[Mã Sinh Viên],pass AS[Password],tentruong AS[Tên Trường],tenkhoa AS[Tên Khoa],tenlop AS[Tên Lớp],ten AS[Tên Sinh Viên],gioitinh AS[Giới Tính],email AS[Email],diachi AS[Địa Chỉ],avatar AS[Avatar],tentinhtrang AS[Tình Trạng] FROM Sinhvien,Lop,Khoa,Truong,Tinhtrang
	WHERE Sinhvien.malop = Lop.malop
	AND Sinhvien.matinhtrang = Tinhtrang.matinhtrang
	AND Lop.makhoa = Khoa.makhoa
	AND Khoa.matruong = Truong.matruong
	-- exec GetAllSinhvien
GO
-- Thu tuc lay thong tin sinh vien trong CSDL
CREATE PROCEDURE GetInfoSinhvien
	@MaSV nvarchar(20)
AS
	SELECT MaSV AS[Mã Sinh Viên],pass AS[Password],tentruong AS[Tên Trường],tenkhoa AS[Tên Khoa],tenlop AS[Tên Lớp],ten AS[Tên Sinh Viên],gioitinh AS[Giới Tính],email AS[Email],diachi AS[Địa Chỉ],tentinhtrang AS[Tình Trạng],avatar AS[Avatar] FROM Sinhvien,Lop,Khoa,Truong,Tinhtrang
	WHERE Sinhvien.malop = Lop.malop
	AND Sinhvien.matinhtrang = Tinhtrang.matinhtrang
	AND Lop.makhoa = Khoa.makhoa
	AND Khoa.matruong = Truong.matruong
	AND MaSV LIKE '%'+@MaSV+'%'
	-- exec GetInfoSinhvien 101071001
	select * from Sinhvien
GO
-- Thu tuc cap nhat thong tin ca nhan cho sinh vien
CREATE PROCEDURE UpdateSinhvien
	@MaSV nvarchar(20),
	@pass nvarchar(30),
	@ten nvarchar(50),
	@gioitinh nvarchar(3),
	@email nvarchar(30),
	@diachi nvarchar(200)
AS
	UPDATE Sinhvien SET pass = @pass,ten = @ten,gioitinh = @gioitinh,email = @email,diachi = @diachi WHERE MaSV LIKE @MaSV
	-- exec UpdateSinhvien '101071001','vietthang',N'Lê Viết Thắng',N'Nam','thang@yahoo.com',N'Điện Bàn - Quảng Nam'
select * from Sinhvien
GO
-- Thu tuc cap nhat thong tin ca nhan(co update tinh trang(thoi hoc,bao luu)) cho sinh vien boi Admin
CREATE PROCEDURE UpdateSinhvienByAdmin
	@MaSV nvarchar(20),
	@pass nvarchar(30),
	@ten nvarchar(50),
	@gioitinh nvarchar(3),
	@email nvarchar(30),
	@diachi nvarchar(200),
	@avatar nvarchar(200),
	@tinhtrang nvarchar(20)
AS
	BEGIN
		DECLARE @matinhtrang int,@malop int,@makhoa int,@matruong int,@date datetime
		SELECT @matinhtrang = matinhtrang FROM Tinhtrang WHERE tentinhtrang = @tinhtrang
		IF(@matinhtrang = 3)
			BEGIN
				SET @date = convert(varchar, getdate(), 101)
				SELECT @malop = malop FROM Sinhvien WHERE MaSV = @MaSV
				SELECT @makhoa = makhoa FROM Lop WHERE malop = @malop
				SELECT @matruong=matruong FROM Khoa WHERE makhoa = @makhoa
				
				INSERT INTO Quyetdinh(MaSV,ngaybd,mayeucau,matruong,makhoa,malop) VALUES(@MaSV,@date,3,@matruong,@makhoa,@malop)
			END
		UPDATE Sinhvien SET pass = @pass,ten = @ten,gioitinh = @gioitinh,email = @email,diachi = @diachi,avatar = @avatar,matinhtrang = @matinhtrang WHERE MaSV = @MaSV
	END
	-- exec UpdateSinhvienByAdmin '101071001','vietthang',N'Lê Viết Thắng',N'Nam','thangvietle1912@gmail.com',N'Điện Bàn - Quảng Nam',N'/Images/Sinhvien/NoAvatarSV.jpg',N'Thôi học'

GO
-- Thu tuc tim kiem sinh vien theo MaSV
CREATE PROCEDURE SearchSinhvienByID
	@MaSV nvarchar(20)
AS
	SELECT MaSV AS[Mã Sinh Viên],pass AS[Password],tentruong AS[Tên Trường],tenkhoa AS[Tên Khoa],tenlop AS[Tên Lớp],ten AS[Tên Sinh Viên],gioitinh AS[Giới Tính],email AS[Email],diachi AS[Địa Chỉ],avatar AS[Avatar],tentinhtrang AS[Tình Trạng] FROM Sinhvien,Lop,Khoa,Truong,Tinhtrang
	WHERE Sinhvien.malop = Lop.malop
	AND Sinhvien.matinhtrang = Tinhtrang.matinhtrang
	AND Lop.makhoa = Khoa.makhoa
	AND Khoa.matruong = Truong.matruong
	AND MaSV LIKE '%'+@MaSV+'%'
	-- exec SearchSinhvienByID '101071001'
	
GO
-- Thu tuc tim kiem sinh vien theo TenSV
CREATE PROCEDURE SearchSinhvienByName
	@TenSV nvarchar(50)
AS
	SELECT MaSV AS[Mã Sinh Viên],pass AS[Password],tentruong AS[Tên Trường],tenkhoa AS[Tên Khoa],tenlop AS[Tên Lớp],ten AS[Tên Sinh Viên],gioitinh AS[Giới Tính],email AS[Email],diachi AS[Địa Chỉ],avatar AS[Avatar],tentinhtrang AS[Tình Trạng] FROM Sinhvien,Lop,Khoa,Truong,Tinhtrang
	WHERE Sinhvien.malop = Lop.malop
	AND Sinhvien.matinhtrang = Tinhtrang.matinhtrang
	AND Lop.makhoa = Khoa.makhoa
	AND Khoa.matruong = Truong.matruong
	AND ten LIKE '%'+@TenSV+'%'
	-- exec SearchSinhvienByName N'Lê'

GO
-- Thu tuc tim kiem sinh vien theo MaSV or TenSV
CREATE PROCEDURE SearchSinhvienByIDOrTen
	@MaSVOrTen nvarchar(20)
AS
	SELECT MaSV AS[Mã Sinh Viên],pass AS[Password],tentruong AS[Tên Trường],tenkhoa AS[Tên Khoa],tenlop AS[Tên Lớp],ten AS[Tên Sinh Viên],gioitinh AS[Giới Tính],email AS[Email],diachi AS[Địa Chỉ],avatar AS[Avatar],tentinhtrang AS[Tình Trạng] FROM Sinhvien,Lop,Khoa,Truong,Tinhtrang
	WHERE (Sinhvien.malop = Lop.malop
	AND Sinhvien.matinhtrang = Tinhtrang.matinhtrang
	AND Lop.makhoa = Khoa.makhoa
	AND Khoa.matruong = Truong.matruong
	AND MaSV LIKE '%'+@MaSVOrTen+'%') 
	OR (Sinhvien.malop = Lop.malop
	AND Sinhvien.matinhtrang = Tinhtrang.matinhtrang
	AND Lop.makhoa = Khoa.makhoa
	AND Khoa.matruong = Truong.matruong
	AND ten LIKE '%'+@MaSVOrTen+'%')
	-- exec SearchSinhvienByIDOrTen 'L'
GO
-- Thu tuc tim kiem sinh vien theo tentruong,tenkhoa,tenlop
CREATE PROCEDURE SearchSinhvienByTruongKhoaLop
	@tentruong nvarchar(50),
	@tenkhoa nvarchar(50),
	@tenlop nvarchar(50),
	@TenSV nvarchar(50)
AS
	BEGIN
		DECLARE @matruong int,@makhoa int,@malop int
		SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		SELECT @makhoa = makhoa FROM Khoa WHERE matruong = @matruong
		SELECT @malop = malop FROM Lop WHERE makhoa = @makhoa
		SELECT MaSV AS[Mã Sinh Viên],pass AS[Password],tentruong AS[Tên Trường],tenkhoa AS[Tên Khoa],tenlop AS[Tên Lớp],ten AS[Tên Sinh Viên],gioitinh AS[Giới Tính],email AS[Email],diachi AS[Địa Chỉ],avatar AS[Avatar],tentinhtrang AS[Tình Trạng] FROM Sinhvien,Lop,Khoa,Truong,Tinhtrang
		WHERE Sinhvien.malop = Lop.malop
		AND Sinhvien.matinhtrang = Tinhtrang.matinhtrang
		AND Lop.makhoa = Khoa.makhoa
		AND Khoa.matruong = Truong.matruong
		AND ten LIKE '%'+@TenSV+'%'
	END
	-- exec SearchSinhvienByTruongKhoaLop N'Đại học Bách Khoa Đà Nẵng',N'Cơ Khí Chế Tạo',N'07C1',N'Viết Thắng'

GO
-- Thu tuc lay sinh vien o trang thai dang hoc
CREATE PROCEDURE SearchSinhvienDangHoc
	@tentruong nvarchar(50),
	@tenkhoa nvarchar(50),
	@tenlop nvarchar(50)
AS
	BEGIN
		DECLARE @matruong int,@makhoa int,@malop int
		SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		SELECT @makhoa = makhoa FROM Khoa WHERE matruong = @matruong
		SELECT @malop = malop FROM Lop WHERE makhoa = @makhoa
		SELECT MaSV AS[Mã Sinh Viên],pass AS[Password],tentruong AS[Tên Trường],tenkhoa AS[Tên Khoa],tenlop AS[Tên Lớp],ten AS[Tên Sinh Viên],gioitinh AS[Giới Tính],email AS[Email],diachi AS[Địa Chỉ],avatar AS[Avatar],tentinhtrang AS[Tình Trạng] FROM Sinhvien,Lop,Khoa,Truong,Tinhtrang
		WHERE Sinhvien.malop = Lop.malop
		AND Sinhvien.matinhtrang = Tinhtrang.matinhtrang
		AND Lop.makhoa = Khoa.makhoa
		AND Khoa.matruong = Truong.matruong
		AND Sinhvien.matinhtrang = 1
	END
	-- exec SearchSinhvienDangHoc N'Đại học Bách Khoa Đà Nẵng',N'Cơ Khí Chế Tạo',N'07C1'
	
GO
-----------------------------------------------------------------------
--                         PROCEDURE Admin                           --
-----------------------------------------------------------------------
-- Thu tuc lay tat ca Admin trong CSDL
CREATE PROCEDURE GetAllAdmin
AS
	SELECT MaAdmin AS[Mã Administrator],pass AS[Password],ten AS[Tên Administrator],gioitinh AS[Giới Tính],email AS[Email],diachi AS[Địa Chỉ],avatar AS[Avatar] FROM Administrator
	-- exec GetAllAdmin
GO
-- Thu tuc Update thong tin ca nhan cho admin
CREATE PROCEDURE UpdateAdmin
	@MaAdmin nvarchar(20),
	@pass nvarchar(20),
	@ten nvarchar(50),
	@gioitinh nvarchar(5),
	@email nvarchar(30),
	@diachi nvarchar(50),
	@avatar nvarchar(200)
AS
	UPDATE Administrator SET pass = @pass,ten = @ten,gioitinh = @gioitinh,email = @email,diachi = @diachi,avatar = @avatar WHERE MaAdmin = @MaAdmin
	-- exec UpdateAdmin 'Administrator001',N'vietthang',N'Lê Viết Thắng',N'Nam','thang123456789@yahoo.com',N'Điện Bàn - Quảng Nam',N'/Images/Admin/NoAvatarAdmin.jpg'
	-- select * from Administrator
	
GO
-- Thu tuc tim kiem Administrator theo MaAdmin
CREATE PROCEDURE SearchAdminByID
	@MaAdmin nvarchar(20)
AS
	SELECT MaAdmin AS[Mã Administrator],pass AS[Password],ten AS[Tên Administrator],gioitinh AS[Giới Tính],email AS[Email],diachi AS[Địa Chỉ],avatar AS[Avatar] FROM Administrator
	WHERE MaAdmin LIKE  '%'+@MaAdmin+'%'
	-- exec SearchAdminByID 'Administrator001'

GO
-- Thu tuc tim kiem Administrator theo Ten Administrator
CREATE PROCEDURE SearchAdminByTen
	@tenAdmin nvarchar(20)
AS
	SELECT MaAdmin AS[Mã Administrator],pass AS[Password],ten AS[Tên Administrator],gioitinh AS[Giới Tính],email AS[Email],diachi AS[Địa Chỉ],avatar AS[Avatar] FROM Administrator
	WHERE ten LIKE '%'+@tenAdmin+'%'
	-- exec SearchAdminByTen N'Lê'

GO
-- Thu tuc tim kiem Administrator theo MaAdmin or TenAdmin
CREATE PROCEDURE SearchAdminByIDOrTen
	@MaAdminOrTen nvarchar(20)
AS
	SELECT MaAdmin AS[Mã Administrator],pass AS[Password],ten AS[Tên Administrator],gioitinh AS[Giới Tính],email AS[Email],diachi AS[Địa Chỉ],avatar AS[Avatar] FROM Administrator
	WHERE (MaAdmin LIKE  '%'+@MaAdminOrTen+'%') OR (ten LIKE '%'+@MaAdminOrTen+'%')
	-- exec SearchAdminByIDOrTen N'V'
GO
-----------------------------------------------------------------------
--                         PROCEDURE Quyetdinh                       --
-----------------------------------------------------------------------
GO
-- Thu tuc them vao quyet dinh (!phuc khao)
ALTER PROCEDURE InsertQuyetdinh
	@MaSV varchar(20),
	@tenyeucau nvarchar(20),
	@tentruong nvarchar(50),
	@tenkhoa nvarchar(30),
	@tenlop nvarchar(30),
	@lydo nvarchar(500)
AS
	BEGIN
		DECLARE @malop int,@makhoa int,@matruong int,@date datetime,@mayeucau int
		SET @date = convert(varchar, getdate(), 101)
		SELECT @mayeucau = mayeucau FROM Yeucau WHERE tenyeucau = @tenyeucau
		SELECT @malop = malop FROM Sinhvien WHERE MaSV = @MaSV
		SELECT @makhoa = makhoa FROM Lop WHERE malop = @malop
		SELECT @matruong = matruong FROM Khoa WHERE makhoa = @makhoa
		INSERT INTO Quyetdinh(MaSV,ngaybd,ngaykt,mayeucau,matruong,makhoa,malop,lydo) VALUES(@MaSV,@date,@date,@mayeucau,@matruong,@makhoa,@malop,@lydo)
	END
GO
-- Thu tuc them vao quyet dinh (bao luu)
ALTER PROCEDURE InsertQuyetdinhBaoLuu
	@MaSV varchar(20),
	@ngaykt varchar(30),
	@tenyeucau nvarchar(20),
	@tentruong nvarchar(50),
	@tenkhoa nvarchar(30),
	@tenlop nvarchar(30),
	@lydo nvarchar(500)
AS
	BEGIN
		DECLARE @malop int,@makhoa int,@matruong int,@date datetime,@mayeucau int,@dateend datetime
		SET @date = convert(varchar, getdate(), 101)
		SET @dateend = CAST(@ngaykt AS datetime)
		SELECT @mayeucau = mayeucau FROM Yeucau WHERE tenyeucau = @tenyeucau
		SELECT @malop = malop FROM Sinhvien WHERE MaSV = @MaSV
		SELECT @makhoa = makhoa FROM Lop WHERE malop = @malop
		SELECT @matruong = matruong FROM Khoa WHERE makhoa = @makhoa
		INSERT INTO Quyetdinh(MaSV,ngaybd,ngaykt,mayeucau,matruong,makhoa,malop,lydo) VALUES(@MaSV,@date,@dateend,@mayeucau,@matruong,@makhoa,@malop,@lydo)
	END
	exec InsertQuyetdinhBaoLuu '101071001','12/19/2011',N'Bảo Lưu',N'Đại học bách khoa đà nẵng',N'Cơ khí chế tạo',N'07C1',''
GO
-- delete from Quyetdinh
-- Thu tuc them vao quyet dinh phuc khao
CREATE PROCEDURE InsertQuyetdinhPhuckhao
	@MaSV varchar(20),
	@tenyeucau nvarchar(20),
	@tentruong nvarchar(50),
	@tenkhoa nvarchar(30),
	@tenlop nvarchar(30),
	@tenMH nvarchar(30),
	@lydo nvarchar(500)
AS
	BEGIN
		DECLARE @malop int,@makhoa int,@matruong int,@date datetime,@mayeucau int,@maMH int
		SET @date = convert(varchar, getdate(), 101)
		SELECT @maMH = maMH FROM Monhoc WHERE tenMH = @tenMH
		SELECT @mayeucau = mayeucau FROM Yeucau WHERE tenyeucau = @tenyeucau
		SELECT @malop = malop FROM Sinhvien WHERE MaSV = @MaSV
		SELECT @makhoa = makhoa FROM Lop WHERE malop = @malop
		SELECT @matruong = matruong FROM Khoa WHERE makhoa = @makhoa
		INSERT INTO Quyetdinh(MaSV,ngaybd,ngaykt,mayeucau,matruong,makhoa,malop,maMH,lydo) VALUES(@MaSV,@date,@date,@mayeucau,@matruong,@makhoa,@malop,@maMH,@lydo)
	END	
	--exec InsertQuyetdinhPhuckhao '101071001',N'Thôi Học',N'Đại học bách khoa đà nẵng',N'Cơ khí chế tạo',N'07C1','',''
	--select * from Quyetdinh
GO
-- Thu tuc lay tat ca cac quyet dinh
-- select * from Tinhtrang
CREATE PROCEDURE GetAllQuyetdinh
AS
	SELECT Quyetdinh.MaSV AS [Mã Sinh Viên],ngaybd AS[Ngày Bắt Đầu],ngaykt AS[Ngày Kết Thúc],Yeucau.tenyeucau AS[Yêu Cầu],Truong.tentruong AS[Tên Trường],Khoa.tenkhoa AS[Tên Khoa],Lop.tenlop AS[Tên Lớp],Monhoc.tenMH AS[Tên Môn Học],lydo AS[Lý Do],tentinhtrang AS[Tình Trạng]
	FROM Quyetdinh,Sinhvien,Lop,Khoa,Yeucau,Tinhtrang,Truong,Monhoc
	WHERE Quyetdinh.MaSV = Sinhvien.MaSV
	AND Quyetdinh.malop  = Lop.malop
	AND Quyetdinh.makhoa = Khoa.makhoa
	AND Quyetdinh.matruong = Truong.matruong
	AND Quyetdinh.mayeucau = Yeucau.mayeucau
	AND Quyetdinh.tinhtrang = Tinhtrang.matinhtrang
	AND Quyetdinh.maMH = Monhoc.MaMH
	ORDER BY Quyetdinh.MaSV,ngaybd DESC

	-- exec GetAllQuyetdinh

GO
-----------------------------------------------------------------------
--                         PROCEDURE Monhoc                          --
-----------------------------------------------------------------------

-- Thu tuc chen vao mon hoc
CREATE PROCEDURE InsertMonhoc
	@tenMH nvarchar(30),
	@soTC int,
	@tenHK nvarchar(10),
	@tentruong nvarchar(50),
	@tenkhoa nvarchar(30)
AS
	BEGIN
		DECLARE @matruong int,@makhoa int,@maHK nvarchar(4)
		SELECT @maHK = maHK FROM Hocky WHERE tenHK = @tenHK
		SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		SELECT @makhoa = makhoa FROM Khoa WHERE matruong = @matruong AND tenkhoa = @tenkhoa
		INSERT INTO Monhoc(tenMH,soTC,maHK,makhoa) VALUES(@tenMH,@soTC,@maHK,@makhoa)
	END
	
	-- exec InsertMonhoc N'Công Nghệ Phần Mềm',2,N'Học Kỳ 1',N'Đại học Bách Khoa Đà Nẵng',N'Công Nghệ Thông Tin'
	-- exec InsertMonhoc N'Trí Tuệ Nhân Tạo',3,N'Học Kỳ 1',N'Đại học Bách Khoa Đà Nẵng',N'Công Nghệ Thông Tin'
	-- select * from Monhoc
GO
-- Thu tuc cap nhat thong tin cho mon hoc
CREATE PROCEDURE UpdateMonhoc
	@maMH int,
	@tenMH nvarchar(30),
	@soTC int,
	@tenHK nvarchar(10)
AS
	BEGIN
		DECLARE @maHK nvarchar(4)
		SELECT @maHK = maHK FROM Hocky WHERE tenHK = @tenHK
		UPDATE Monhoc SET tenMH = @tenMH, soTC = @soTC, maHK = @maHK WHERE MaMH = @maMH
	END
	-- exec UpdateMonhoc 1,N'Công Nghệ Phần Mềm',3,N'Học Kỳ 3'
	
GO
-- Thu tuc xoa mon hoc
CREATE PROCEDURE DeleteMonhoc
	@maMH int
AS
	DELETE FROM Monhoc WHERE MaMH = @maMH
	-- exec DeleteMonhoc 2
	
GO
-- Tim kiem mon hoc dua theo truong,khoa
CREATE PROCEDURE SearchMonhocByTruongKhoaTenMH
	@tentruong nvarchar(30),
	@tenkhoa nvarchar(30),
	@tenMH nvarchar(30)
AS
	BEGIN
		DECLARE @matruong int,@makhoa int,@maMH int
		SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		SELECT @makhoa = makhoa FROM Khoa WHERE tenkhoa = @tenkhoa AND matruong = @matruong
		SELECT MaMH AS[Mã Môn Học],tenMH AS[Tên Môn Học],soTC AS[Số Tín Chỉ],tenHK AS[Tên Học Kỳ],tenkhoa AS[Tên Khoa] FROM Monhoc,Hocky,Khoa
		WHERE Monhoc.maHK = Hocky.maHK
		AND Monhoc.makhoa = Khoa.makhoa
		AND tenMH LIKE '%'+@tenMH+'%'
	END
	-- exec SearchMonhocByTruongKhoa N'Đại học Bách Khoa Đà Nẵng',N'Công Nghệ Thông Tin',N'C'
GO
-- Tim kiem mon hoc dua theo truong,khoa
CREATE PROCEDURE SearchMonhocByTruongKhoa
	@tentruong nvarchar(30),
	@tenkhoa nvarchar(30)
AS
	BEGIN
		DECLARE @matruong int,@makhoa int,@maMH int
		SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		SELECT @makhoa = makhoa FROM Khoa WHERE tenkhoa = @tenkhoa AND matruong = @matruong
		SELECT MaMH AS[Mã Môn Học],tenMH AS[Tên Môn Học],soTC AS[Số Tín Chỉ],tenHK AS[Tên Học Kỳ],tenkhoa AS[Tên Khoa] FROM Monhoc,Hocky,Khoa
		WHERE Monhoc.maHK = Hocky.maHK
		AND Monhoc.makhoa = Khoa.makhoa
	END
	-- exec SearchMonhocByTruongKhoa N'Đại học Bách Khoa Đà Nẵng',N'Công Nghệ Thông Tin'
GO
-- Thu tuc tim kiem theo ten va ma mon hoc
-- Tim kiem mon hoc dua theo truong,khoa
CREATE PROCEDURE SearchMonhocByID
	@tentruong nvarchar(30),
	@tenkhoa nvarchar(30),
	@id int
AS
	BEGIN
		DECLARE @matruong int,@makhoa int,@maMH int
		SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		SELECT @makhoa = makhoa FROM Khoa WHERE tenkhoa = @tenkhoa AND matruong = @matruong
		SELECT MaMH AS[Mã Môn Học],tenMH AS[Tên Môn Học],soTC AS[Số Tín Chỉ],tenHK AS[Tên Học Kỳ],tenkhoa AS[Tên Khoa] FROM Monhoc,Hocky,Khoa
		WHERE Monhoc.maHK = Hocky.maHK
		AND Monhoc.makhoa = Khoa.makhoa
		AND Monhoc.MaMH LIKE @id
	END
	-- exec SearchMonhocByID N'Đại học Bách Khoa Đà Nẵng',N'Công Nghệ Thông Tin',1
	
GO
-----------------------------------------------------------------------
--                         PROCEDURE Diem                            --
-----------------------------------------------------------------------

-- Thu tuc chen diem cua mon hoc
CREATE PROCEDURE InsertDiem
	@tentruong nvarchar(30),
	@tenkhoa nvarchar(30),
	@MaSV varchar(20),
	@tenMH nvarchar(20),
	@DiemBT float,
	@DiemGK float,
	@DiemCK float
AS
	BEGIN
		DECLARE @matruong int,@makhoa int,@maMH int
		SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		SELECT @makhoa = makhoa FROM Khoa WHERE matruong = @matruong AND tenkhoa = @tenkhoa
		SELECT @maMH = maMH FROM Monhoc WHERE tenMH = @tenMH
		INSERT INTO Diem VALUES(@MaSV,@maMH,@DiemBT,@DiemGK,@DiemCK)
	END
	
	-- exec InsertDiem N'Đại học Bách Khoa Đà Nẵng',N'Công Nghệ Thông Tin','101071001',N'Công Nghệ Phần Mềm',7.5,7.5,9
GO
-- Thu tuc cap nhat diem cho sinh vien
CREATE PROCEDURE UpdateDiem
	@tentruong nvarchar(30),
	@tenkhoa nvarchar(30),
	@MaSV varchar(20),
	@tenMH nvarchar(20),
	@DiemBT float,
	@DiemGK float,
	@DiemCK float
AS
	BEGIN
		DECLARE @matruong int,@makhoa int,@maMH int
		SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		SELECT @makhoa = makhoa FROM Khoa WHERE matruong = @matruong AND tenkhoa = @tenkhoa
		SELECT @maMH = maMH FROM Monhoc WHERE tenMH = @tenMH
		UPDATE Diem SET DiemBT = @DiemBT,DiemGK=@DiemGK,DiemCK=@DiemCK WHERE MaSV = @MaSV AND MaMH = @maMH
	END
	-- exec UpdateDiem N'Đại học Bách Khoa Đà Nẵng',N'Công Nghệ Thông Tin','101071001',N'Công Nghệ Phần Mềm',7.5,7.5,10
	-- select * from Diem
GO
-- Thu tuc Lay diem theo hoc ky
CREATE PROCEDURE GetDiemByKyHoc
	@tentruong nvarchar(30),
	@tenkhoa nvarchar(30),
	@tenHK nvarchar(10)
AS
	BEGIN
		DECLARE @matruong int,@makhoa int,@maHK nvarchar(4)
		SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		SELECT @makhoa = makhoa FROM Khoa WHERE matruong = @matruong AND tenkhoa = @tenkhoa
		SELECT @maHK = maHK FROM Hocky WHERE tenHK = @tenHK
		SELECT Diem.MaSV AS[Mã Sinh Viên],Sinhvien.ten AS[Tên Sinh Viên],Monhoc.tenMH AS[Tên Môn Học],DiemBT AS[Điểm Bài Tập],DiemGK AS[Điểm Giữa Kỳ],DiemCK AS[Điểm Cuối Kỳ] FROM Diem,Monhoc,Hocky,Sinhvien
		WHERE Diem.MaMH = Monhoc.MaMH
		AND Diem.MaSV = Sinhvien.MaSV
		AND Monhoc.maHK = Hocky.maHK
		AND Monhoc.maHK = @maHK
	END
	--exec GetDiemByKyHoc N'Đại học Bách Khoa Đà Nẵng',N'Công Nghệ Thông Tin',N'Học Kỳ 3'

GO
-- Thu tuc Lay diem theo hoc ky,Ma sinh vien
CREATE PROCEDURE GetDiemByKyHocSV
	@MaSV nvarchar(20),
	@tentruong nvarchar(30),
	@tenkhoa nvarchar(30),
	@tenHK nvarchar(10)
AS
	BEGIN
		DECLARE @matruong int,@makhoa int,@maHK nvarchar(4)
		SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		SELECT @makhoa = makhoa FROM Khoa WHERE matruong = @matruong AND tenkhoa = @tenkhoa
		SELECT @maHK = maHK FROM Hocky WHERE tenHK = @tenHK
		SELECT Diem.MaSV AS[Mã Sinh Viên],Sinhvien.ten AS[Tên Sinh Viên],Diem.MaMH AS[Mã Môn Học],Monhoc.tenMH AS[Tên Môn Học],DiemBT AS[Điểm Bài Tập],DiemGK AS[Điểm Giữa Kỳ],DiemCK AS[Điểm Cuối Kỳ],DiemBT*0.2+DiemGK*0.3+DiemCK*0.5 AS[Điểm Trung Bình] FROM Diem,Monhoc,Hocky,Sinhvien
		WHERE Diem.MaMH = Monhoc.MaMH
		AND Diem.MaSV = Sinhvien.MaSV
		AND Monhoc.maHK = Hocky.maHK
		AND Monhoc.maHK = @maHK
		AND Diem.MaSV = @MaSV
	END

GO
-- Thu tuc lay tat ca cac diem cua sinh vien
-- Thu tuc Lay diem theo hoc ky,Ma sinh vien
CREATE PROCEDURE GetDiemBySV
	@MaSV nvarchar(20),
	@tentruong nvarchar(30),
	@tenkhoa nvarchar(30)
AS
	BEGIN
		DECLARE @matruong int,@makhoa int
		SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		SELECT @makhoa = makhoa FROM Khoa WHERE matruong = @matruong AND tenkhoa = @tenkhoa
		SELECT Diem.MaSV AS[Mã Sinh Viên],Sinhvien.ten AS[Tên Sinh Viên],Diem.MaMH AS[Mã Môn Học],Monhoc.tenMH AS[Tên Môn Học],DiemBT AS[Điểm Bài Tập],DiemGK AS[Điểm Giữa Kỳ],DiemCK AS[Điểm Cuối Kỳ],DiemBT*0.2+DiemGK*0.3+DiemCK*0.5 AS[Điểm Trung Bình] FROM Diem,Monhoc,Hocky,Sinhvien
		WHERE Diem.MaMH = Monhoc.MaMH
		AND Diem.MaSV = Sinhvien.MaSV
		AND Monhoc.maHK = Hocky.maHK
		AND Diem.MaSV = @MaSV
	END
	-- exec GetDiemBySV '101071001',N'Đại học bách khoa đà nẵng',N'Cơ khí chế tạo'
GO
-- Thu tuc kiem tra sinh vien da nhap diem chua
CREATE PROCEDURE CheckSinhvienInDiem
	@tentruong nvarchar(30),
	@tenkhoa nvarchar(30),
	@MaSV varchar(20),
	@tenMH nvarchar(20)
AS
	BEGIN
		DECLARE @matruong int,@makhoa int,@maMH int
		SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		SELECT @makhoa = makhoa FROM Khoa WHERE matruong = @matruong AND tenkhoa = @tenkhoa
		SELECT @maMH = maMH FROM Monhoc WHERE tenMH = @tenMH
		SELECT MaSV AS[Mã Sinh Viên],tenMH AS[Tên Môn Học],DiemBT AS[Điểm Bài Tập],DiemGK AS[Điểm Giữa Kỳ],DiemCK AS[Điểm Cuối Kỳ] FROM Diem,Monhoc
		WHERE Diem.MaMH = Monhoc.MaMH
		AND Diem.MaSV = @MaSV
		AND Diem.MaMH = @maMH
	END

	-- exec CheckSinhvienInDiem N'Đại học Bách Khoa Đà Nẵng',N'Công Nghệ Thông Tin','101071001',N'Công Nghệ Phần Mềm'
	
GO
-- Thu tuc lay hoc ky theo truong
CREATE PROCEDURE GetHocKyByTruong
	@tentruong nvarchar(30)
AS
	BEGIN
		DECLARE @matruong int
		SELECT @matruong = matruong FROM Truong WHERE tentruong = @tentruong
		IF(@matruong = 1)
			SELECT maHK AS[Mã Học Kỳ], tenHK AS[Tên Học Kỳ] FROM Hocky
		ELSE IF((@matruong = 2)OR(@matruong = 3))
			SELECT TOP 8 maHK AS[Mã Học Kỳ], tenHK AS[Tên Học Kỳ] FROM Hocky
		ELSE
			SELECT TOP 6 maHK AS[Mã Học Kỳ], tenHK AS[Tên Học Kỳ] FROM Hocky
	END
	-- exec GetHocKyByTruong N'Cao Đẳng Công Nghệ'
GO

-----------------------------------------------------------------------
--                         PROCEDURE Check Login                     --
-----------------------------------------------------------------------

-- Thu tuc kiem tra Sinhvien dang nhap
CREATE PROCEDURE CheckSinhvienLogin
	@MaSV varchar(20),
	@pass nvarchar(30)
AS
	SELECT * FROM Sinhvien WHERE MaSV = @MaSV AND pass = @pass
	
	-- exec CheckSinhvienLogin '101071001','123456'

GO
-- Thu tuc kiem tra Admin dang nhap
CREATE PROCEDURE CheckAdminLogin
	@MaAdmin varchar(20),
	@pass nvarchar(30)
AS
	SELECT * FROM Administrator WHERE MaAdmin = @MaAdmin AND pass = @pass
	
	-- exec CheckAdminLogin 'Administrator001','123456'
	
GO
-----------------------------------------------------------------------
--                         PROCEDURE Yeu Cau                         --
-----------------------------------------------------------------------
CREATE PROCEDURE GetAllYeucauSV
AS
	SELECT mayeucau AS[Mã Yêu Cầu], tenyeucau AS[Tên Yêu Cầu] FROM Yeucau WHERE tenyeucau != N'Hiệu Trưởng'
	-- exec GetAllYeucauSV
	
