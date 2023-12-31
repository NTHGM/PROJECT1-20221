
CREATE DATABASE ThuVien
GO

USE ThuVien
GO

CREATE TABLE TACGIA(
	MATACGIA INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	TENTACGIA NVARCHAR(50)
);

CREATE TABLE THELOAI(
	MATHELOAI INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	TENTHELOAI NVARCHAR(50)
);

CREATE TABLE NXB(
	MANXB INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	TENNXB NVARCHAR(50),
	DIACHI NVARCHAR(200)
);

CREATE TABLE SACH(
	MASACH INT NOT NULL IDENTITY(1,1) ,
	TENSACH NVARCHAR(50) NOT NULL ,
	NAMXUATBAN INT,
	MANXB INT,
	SOLUONG int,
	MATHELOAI INT,
	PRIMARY KEY(MASACH)
);

CREATE TABLE TACGIA_SACH(
	MASACH INT,
	MATACGIA INT
);

create TABLE BANDOC(
	MABANDOC INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	TENBANDOC NVARCHAR(50),
	NGAYSINH DATE,
	NGAYGIAHAN DATE,
	NGAYHETHAN DATE,
	GIOITINH CHAR(6),
	DIACHI NVARCHAR(200),
	USERNAME NVARCHAR(200)
);

CREATE TABLE ACCOUNT(
	USERNAME NVARCHAR(200) NOT NULL PRIMARY KEY,
	PASS NVARCHAR(200) 
);

CREATE TABLE THEMUONTRA(
	MAMUONTRA INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	NGAYMUON DATE,
	NGAYTRA DATE,
	GHICHU NVARCHAR(MAX),
	TIENCOC MONEY,
	TIENPHAT MONEY,
	DANGMUON BIT,
	MATHUTHU INT,
	MABANDOC INT,
	MASACH INT
);

CREATE TABLE THUTHU(
	MATHUTHU INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	TENTHUTHU NVARCHAR(50),
	DIACHI NVARCHAR(200),
	SDT NVARCHAR(11),
	USERNAME NVARCHAR(200)
);

alter table SACH add constraint SACH_NXB foreign key (MANXB) references NXB(MANXB)
go

alter table SACH add constraint SACH_THELOAI foreign key (MATHELOAI) references THELOAI(MATHELOAI)
go

alter table TACGIA_SACH add constraint TACGIA_SACH_SACH foreign key (MASACH) references SACH(MASACH)
go
alter table TACGIA_SACH add constraint TACGIA_SACH_TACGIA foreign key (MATACGIA) references TACGIA(MATACGIA)
go

alter table BANDOC add constraint BANDOC_ACCOUNT foreign key (USERNAME) references ACCOUNT (USERNAME)
go

alter table THUTHU add constraint THUTHU_ACCOUNT foreign key (USERNAME) references ACCOUNT (USERNAME)
go

alter table THEMUONTRA add constraint THEMUONTRA_THUTHU foreign key (MATHUTHU) references THUTHU (MATHUTHU)
go

alter table THEMUONTRA add constraint THEMUONTRA_SACH foreign key (MASACH) references SACH (MASACH)
go

alter table THEMUONTRA add constraint THEMUONTRA_BANDOC foreign key (MABANDOC) references BANDOC (MABANDOC)
go

select * from BANDOC
select * from ACCOUNT
select * from THEMUONTRA
select * from THELOAI
select * from THUTHU
select * from NXB
select * from TACGIA
select * from TACGIA_SACH
select * from SACH

insert into ACCOUNT(USERNAME,PASS) values ('admin','1');
insert into THUTHU(TENTHUTHU,DIACHI,SDT,USERNAME) values ('Huy','Thái Nguyên','000912324','admin');

insert into ACCOUNT(USERNAME,PASS) values ('nguyenvana','1');
insert into BANDOC(TENBANDOC, NGAYSINH, NGAYGIAHAN, NGAYHETHAN, GIOITINH, DIACHI, USERNAME)
values ('Nguyễn Văn A', '2000-01-01', '2023-01-22', '2023-02-22', 'Nam', 'Hà Nội', 'nguyenvana');

insert into ACCOUNT(USERNAME,PASS) values ('vuong','1');
insert into BANDOC(TENBANDOC, NGAYSINH, NGAYGIAHAN, NGAYHETHAN, GIOITINH, DIACHI, USERNAME)
values ('Hoàng Thị Vượng', '2002-04-03', '2022-01-22', '2022-09-22', 'Nữ', 'Hà Nội', 'vuong');

insert into TACGIA(TENTACGIA) values ('Nguyễn Nhật Ánh')
insert into TACGIA(TENTACGIA) values ('Tô Hoài')

insert into THELOAI(TENTHELOAI) values ('Tiểu thuyết')
insert into THELOAI(TENTHELOAI) values ('Thiếu Nhi')

insert into NXB(TENNXB, DIACHI) values ('NXB Trẻ', 'Hà Nội')
insert into NXB(TENNXB, DIACHI) values ('NXB Kim Đồng', 'Hà Nội')


insert into SACH(TENSACH, NAMXUATBAN, MANXB, SOLUONG, MATHELOAI) 
values ('Cho tôi xin một vé đi tuổi thơ', 2011, 1, 10, 1)

insert into TACGIA_SACH(MASACH, MATACGIA) values (1, 1)

insert into THEMUONTRA(NGAYMUON, NGAYTRA, GHICHU, TIENCOC, TIENPHAT, DANGMUON, MATHUTHU, MABANDOC, MASACH)
values ('2023-02-20', '2023-03-03', NULL, 20000, NULL, 1, 1, 1, 1)