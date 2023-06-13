package sql;

public class SQLCommand {
	//ACCOUNT
	public static String ACCOUNT_BANDOC_CHECKTONTAIUSER = "select USERNAME from ACCOUNT where USERNAME = ?";
	public static String ACCOUNT_BANDOC_CHECK= "select USERNAME from ACCOUNT where USERNAME = ? and PASS = ?";
	public static String ACCOUNT_BANDOC_DANGNHAP = "SELECT distinct bc.* FROM BANDOC bc, ACCOUNT a "
			+ "WHERE bc.USERNAME = a.USERNAME "
			+ "AND a.USERNAME = ? AND a.PASS = ?";
	public static String ACCOUNT_THUTHU_DANGNHAP = "SELECT distinct tt.MATHUTHU AS MATHUTHU,tt.TENTHUTHU as TENTHUTHU,tt.DIACHI as DIACHI,tt.SDT as SDT,tt.USERNAME as USERNAME FROM THUTHU tt, ACCOUNT a "
			+ "WHERE tt.USERNAME = a.USERNAME "
			+ "AND a.USERNAME = ? AND a.PASS = ?";
	public static String ACCOUNT_BANDOC_DANGKI ="insert into ACCOUNT(USERNAME,PASS) values(?,?)";
	public static String ACCOUNT_DOIMATKHAU = "UPDATE ACCOUNT SET PASS = ? WHERE USERNAME = ?";
	public static String ACCOUNT_DELETE = "DELETE FROM ACCOUNT WHERE USERNAME = ?";
	
	//BANDOC
	public static String BANDOC_THEMBANDOC_USERNAME = "insert into BANDOC(USERNAME) values(?)";
	public static String BANDOC_THEMBANDOC = "insert into BANDOC(TENBANDOC,NGAYSINH,GIOITINH,DIACHI,USERNAME) values(?,?,?,?,?)";
	public static String BANDOC_SUABANDOC = "UPDATE BANDOC SET TENBANDOC = ?,NGAYSINH = ?,GIOITINH = ?,DIACHI = ? WHERE MABANDOC =?";
	public static String BANDOC_XEMTT = "SELECT * FROM BANDOC";
	public static String BANDOC_XOA = "delete from BANDOC where MABANDOC = ?";
	public static String BANDOC_DUOI12TUOI = "select * from BANDOC where (YEAR(GETDATE())-YEAR(NGAYSINH)) < 12";
	public static String BANDOC_12_18TUOI = "select * from BANDOC where (YEAR(GETDATE())-YEAR(NGAYSINH)) between 12 and 18";
	public static String BANDOC_TREN18TUOI = "select * from BANDOC where (YEAR(GETDATE())-YEAR(NGAYSINH)) > 18";

	public static String BANDOC_TIMKIEM_MA = "SELECT * FROM BANDOC where MABANDOC =?";
	public static String BANDOC_TIMKIEM_TEN = "SELECT * FROM BANDOC where TENBANDOC like ?";
	
	public static String BANDOC_GIAHAN = "update BANDOC set NGAYGIAHAN = ?,NGAYHETHAN = ? where MABANDOC =?";
	
	//SACH
	public static String SACH_LAST_INSERT = "Select MAX(s.MASACH) as MASACH from SACH s";
	public static String SACH_THEMSACH = "insert INTO SACH(TENSACH,NAMXUATBAN,MANXB,MATHELOAI) VALUES (?,?,?,?)";
	public static String SACH_XOA = "DELETE FROM SACH WHERE MASACH = ?";
	public static String SACH_XEMTT = "select s.*,t.*,n.*,tg.* from SACH s left join THELOAI t on s.MATHELOAI = t.MATHELOAI left join NXB n on s.MANXB = n.MANXB left join TACGIA_SACH ts on s.MASACH=ts.MASACH left join TACGIA tg on tg.MATACGIA=ts.MATACGIA  ";
	
	public static String SACH_SUA = "update SACH set TENSACH = ?,NAMXUATBAN = ?,MANXB=?,MATHELOAI = ? where MASACH = ?";
	public static String SACH_TIM_MA = "select s.*,t.*,n.*,tg.* from SACH s left join THELOAI t on s.MATHELOAI = t.MATHELOAI left join NXB n on s.MANXB = n.MANXB left join TACGIA_SACH ts on s.MASACH=ts.MASACH left join TACGIA tg on tg.MATACGIA=ts.MATACGIA where s.MASACH=?";
	public static String SACH_TIM_TEN = "select s.*,t.*,n.*,tg.* from SACH s left join THELOAI t on s.MATHELOAI = t.MATHELOAI left join NXB n on s.MANXB = n.MANXB left join TACGIA_SACH ts on s.MASACH=ts.MASACH left join TACGIA tg on tg.MATACGIA=ts.MATACGIA  where s.TENSACH like ?";
	public static String SACH_TIM_THELOAI = "select s.*,t.*,n.*,tg.* from SACH s left join THELOAI t on s.MATHELOAI = t.MATHELOAI left join NXB n on s.MANXB = n.MANXB left join TACGIA_SACH ts on s.MASACH=ts.MASACH left join TACGIA tg on tg.MATACGIA=ts.MATACGIA  where t.TENTHELOAI like ?";
	public static String SACH_TIM_NXB = "select s.*,t.*,n.*,tg.* from SACH s left join THELOAI t on s.MATHELOAI = t.MATHELOAI left join NXB n on s.MANXB = n.MANXB left join TACGIA_SACH ts on s.MASACH=ts.MASACH left join TACGIA tg on tg.MATACGIA=ts.MATACGIA  where n.TENNXB like ?";
	public static String SACH_THEMSOLUONG = "update SACH set SOLUONG = ? where MASACH = ?";
	
	public static String SACH_SOSACHMUON = "select * from THEMUONTRA where DANGMUON = 1 and MASACH = ?";
	
	public static String SACH_TIM_TACGIA="select ts.* from TACGIA_SACH ts,TACGIA t where t.MATACGIA = ts.MATACGIA and t.TENTACGIA like ?";
	//TACGIA_SACH
	public static String TS_THEM = "insert into TACGIA_SACH(MASACH,MATACGIA) values(?,?)";
	public static String TS_XOA = "delete from TACGIA_SACH where MASACH = ? and MATACGIA = ?";
	public static String TS_XOA_MASACH = "Delete from TACGIA_SACH where MASACH = ?";
	public static String TS_XOA_MATACGIA = "Delete from TACGIA_SACH where MATACGIA = ?";
	public static String TS_LIST_TACGIA = "select TACGIA.* from TACGIA_SACH,TACGIA where TACGIA.MATACGIA=TACGIA_SACH.MATACGIA and TACGIA_SACH.MASACH = ?";
	
	
	
	//TACGIA
	public static String TACGIA_XEMTT = "SELECT t.MATACGIA AS MATACGIA, t.TENTACGIA AS TENTACGIA, COUNT(ts.MASACH) AS SOLUONGSACH FROM TACGIA t LEFT JOIN TACGIA_SACH ts ON t.MATACGIA = ts.MATACGIA GROUP BY t.MATACGIA, t.TENTACGIA";
	public static String TACGIA_DS_SACH ="SELECT S.MASACH AS MASACH,S.TENSACH AS TENSACH,S.NAMXUATBAN AS NAMXUATBAN,TL.TENTHELOAI AS THELOAI,N.TENNXB AS NXB FROM SACH S, NXB N,THELOAI TL WHERE S.MATHELOAI = TL.MATHELOAI AND S.MANXB = N.MANXB AND S.MASACH IN (SELECT SACH.MASACH FROM SACH,TACGIA_SACH,TACGIA WHERE SACH.MASACH=TACGIA_SACH.MASACH AND TACGIA_SACH.MATACGIA = TACGIA.MATACGIA AND TACGIA.MATACGIA = ?)";
	public static String TACGIA_THEMTACGIA = "INSERT INTO TACGIA(TENTACGIA) VALUES(?)";
	public static String TACGIA_SUATACGIA = "UPDATE TACGIA SET TENTACGIA = ? WHERE MATACGIA = ?";
	public static String TACGIA_XOATACGIA = "DELETE FROM TACGIA WHERE MATACGIA = ?";
	public static String TACGIA_TIM_MA = "SELECT t.MATACGIA AS MATACGIA, t.TENTACGIA AS TENTACGIA, COUNT(ts.MASACH) AS SOLUONGSACH "
			+ "FROM TACGIA t LEFT JOIN TACGIA_SACH ts ON t.MATACGIA = ts.MATACGIA  where t.MATACGIA = ? GROUP BY t.MATACGIA, t.TENTACGIA";
	
	public static String TACGIA_TIM_TEN = "SELECT t.MATACGIA AS MATACGIA, t.TENTACGIA AS TENTACGIA, COUNT(ts.MASACH) AS SOLUONGSACH "
			+ "FROM TACGIA t LEFT JOIN TACGIA_SACH ts ON t.MATACGIA = ts.MATACGIA where t.TENTACGIA like ? GROUP BY t.MATACGIA, t.TENTACGIA ";
	
	//THELOAI
	public static String THELOAI_XEMTT = "select tl.MATHELOAI AS MATHELOAI, tl.TENTHELOAI AS TENTHELOAI, count(s.MASACH) AS SOLUONGSACH from THELOAI tl left join SACH s on s.MATHELOAI = tl.MATHELOAI group by tl.MATHELOAI, tl.TENTHELOAI";
	public static String THELOAI_DS_SACH = "SELECT s.MASACH AS MASACH, s.TENSACH AS TENSACH, s.NAMXUATBAN AS NAMXUATBAN, n.TENNXB AS NXB,t.TENTHELOAI AS THELOAI,tg.MATACGIA as MATACGIA, tg.TENTACGIA AS TENTACGIA,count(q.MAQUYEN) as SOLUONGQUYEN FROM THELOAI t,NXB n,TACGIA_SACH ts,TACGIA tg,SACH s left join QUYEN q on q.MASACH = s.MASACH where s.MATHELOAI = t.MATHELOAI AND s.MASACH = ts.MASACH AND ts.MATACGIA = tg.MATACGIA AND s.MANXB = n.MANXB and t.MATHELOAI=? group by s.MASACH,s.TENSACH,s.NAMXUATBAN,n.TENNXB,t.TENTHELOAI,tg.MATACGIA,tg.TENTACGIA";
	public static String THELOAI_THEM = "INSERT INTO THELOAI(TENTHELOAI) VALUES(?)";
	public static String THELOAI_SUA = "UPDATE THELOAI SET TENTHELOAI = ? WHERE MATHELOAI = ?";
	public static String THELOAI_XOA = "DELETE FROM THELOAI WHERE MATHELOAI = ?";
	public static String THELOAI_XOASACH = "DELETE FROM SACH WHERE MATHELOAI = ?";
	public static String THELOAI_TIM_MA = "select tl.MATHELOAI AS MATHELOAI, tl.TENTHELOAI AS TENTHELOAI, count(s.MASACH) AS SOLUONGSACH "
			+ "from THELOAI tl left join SACH s on s.MATHELOAI = tl.MATHELOAI where tl.MATHELOAI = ? group by tl.MATHELOAI, tl.TENTHELOAI ";
	
	public static String THELOAI_TIM_TEN = "select tl.MATHELOAI AS MATHELOAI, tl.TENTHELOAI AS TENTHELOAI, count(s.MASACH) AS SOLUONGSACH "
			+ "from THELOAI tl left join SACH s on s.MATHELOAI = tl.MATHELOAI  where tl.TENTHELOAI like ? group by tl.MATHELOAI, tl.TENTHELOAI";
	
	public static String THELOAI_SETNULL = "update SACH set MATHELOAI = NULL WHERE MATHELOAI = ?";
	
	
	//NXB
	public static String NXB_XEMTT = "select n.MANXB AS MANXB, n.TENNXB AS TENNXB,n.DIACHI as DIACHI, count(s.MASACH) AS SOLUONGSACH from NXB n left join SACH s on s.MANXB = n.MANXB group by n.MANXB, n.TENNXB,n.DIACHI";
	public static String NXB_DS_SACH = "SELECT s.MASACH AS MASACH, s.TENSACH AS TENSACH, s.NAMXUATBAN AS NAMXUATBAN, n.TENNXB AS NXB,t.TENTHELOAI AS THELOAI,tg.MATACGIA as MATACGIA, tg.TENTACGIA AS TENTACGIA,count(q.MAQUYEN) as SOLUONGQUYEN FROM THELOAI t,NXB n,TACGIA_SACH ts,TACGIA tg,SACH s left join QUYEN q on q.MASACH = s.MASACH where s.MATHELOAI = t.MATHELOAI AND s.MASACH = ts.MASACH AND ts.MATACGIA = tg.MATACGIA AND s.MANXB = n.MANXB and n.MANXB=? group by s.MASACH,s.TENSACH,s.NAMXUATBAN,n.TENNXB,t.TENTHELOAI,tg.MATACGIA,tg.TENTACGIA";
	public static String NXB_THEM = "INSERT INTO NXB(TENNXB,DIACHI) VALUES(?,?)";
	public static String NXB_SUA = "UPDATE NXB SET TENNXB = ?, DIACHI =? WHERE MANXB = ?";
	public static String NXB_XOA = "DELETE FROM NXB WHERE MANXB = ?";
	public static String NXB_XOASACH = "DELETE FROM SACH WHERE MANXB = ?";
	public static String NXB_TIM_MA = "select n.MANXB AS MANXB, n.TENNXB AS TENNXB,n.DIACHI as DIACHI, count(s.MASACH) AS SOLUONGSACH "
			+ "from NXB n left join SACH s on s.MANXB = n.MANXB where n.MANXB = ? group by n.MANXB, n.TENNXB,n.DIACHI ";
	public static String NXB_TIM_TEN = "select n.MANXB AS MANXB, n.TENNXB AS TENNXB,n.DIACHI as DIACHI, count(s.MASACH) AS SOLUONGSACH "
			+ "from NXB n left join SACH s on s.MANXB = n.MANXB where n.TENNXB like ? group by n.MANXB, n.TENNXB,n.DIACHI ";
	
	public static String NXB_SETNULL = "update SACH set MANXB = NULL WHERE MANXB = ?";
	
	
	//THEMUONTRA
	public static String THEMUONTRA_XEMTT = "select * from THEMUONTRA";
	public static String THEMUONTRA_THEM = "insert into THEMUONTRA(NGAYMUON,NGAYTRA,GHICHU,TIENCOC,DANGMUON,MATHUTHU,MABANDOC,MASACH) values(?,?,?,?,1,?,?,?)";
	public static String THEMUONTRA_XOA_MA = "DELETE FROM THEMUONTRA WHERE MAMUONTRA=?";
	public static String THEMUONTRA_XOA_BANDOC = "DELETE FROM THEMUONTRA WHERE MABANDOC = ?";
	public static String THEMUONTRA_XOA_SACH = "delete from THEMUONTRA where MASACH=?";
	public static String THEMUONTRA_TIM_MA = "select * from THEMUONTRA where MAMUONTRA = ?";
	public static String THEMUONTRA_TIM_SACH = "select t.*,s.* from THEMUONTRA t,SACH s where s.MASACH=t.MASACH and s.TENSACH like ?";
	
	public static String THEMUONTRA_TIM_BANDOC = "select * from THEMUONTRA where MABANDOC = ?";

	public static String THEMUONTRA_TRASACH = "update THEMUONTRA set GHICHU = ?,DANGMUON = 0,TIENPHAT = ? where MAMUONTRA = ?";
	
	//THUTHU
	public static String THUTHU_TIM_MA = "select * from THUTHU where MATHUTHU = ?";

	//THONGKE
	public static String TK_TONGSACH = "select count(*) AS TONG from SACH";
	public static String TK_TONGTG = "select count(*) AS TONG from TACGIA";
	public static String TK_TONGTL = "select count(*) AS TONG from THELOAI";
	public static String TK_TONGNXB = "select count(*) AS TONG from NXB";
	
	public static String TK_TONGBD = "select count(*) AS TONG from BANDOC";
	public static String TK_TONGNAM = "select count(*) AS TONG from BANDOC where GIOITINH = N'Nam'";
	public static String TK_TONGNU = "select count(*) AS TONG from BANDOC where GIOITINH = N'Nu'";
	public static String TK_TONGKHAC = "select count(*) AS TONG from BANDOC where GIOITINH = N'Khac'";
	public static String TK_TONGDUOI18 = "select count(*) AS TONG from BANDOC where FLOOR(DATEDIFF(Day, NgaySinh, GETDATE()) / 365.25) < 18";
	public static String TK_TONG18 = "select count(*) AS TONG from BANDOC where FLOOR(DATEDIFF(Day, NgaySinh, GETDATE()) / 365.25) >= 18";
	
	public static String TK_TONGTMT = "select count(*) AS TONG from THEMUONTRA";
	public static String TK_TONGDATRA = "select count(*) AS TONG from THEMUONTRA where DANGMUON = '0' ";
	public static String TK_TONGCHUATRA = "select count(*) AS TONG from THEMUONTRA where DANGMUON = '1' ";
	public static String TK_TONGQUAHAN = "select count(*) AS TONG from THEMUONTRA tmt join BANDOC bd on tmt.MABANDOC = bd.MABANDOC where tmt.DANGMUON = '1' and ((tmt.NGAYTRA > bd.NGAYHETHAN and (bd.NGAYHETHAN > bd.NGAYGIAHAN or bd.NGAYGIAHAN is null)) or (tmt.NGAYTRA > bd.NGAYGIAHAN and bd.NGAYGIAHAN > bd.NGAYHETHAN))";
	
}
