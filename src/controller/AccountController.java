package controller;

import javax.sql.rowset.JdbcRowSet;

import model.BANDOC;
import model.THUTHU;
import sql.ConnectSQL;

public class AccountController extends Controller{
	public AccountController(ConnectSQL c) {
		super(c);
	}

	public BANDOC checkLoginBanDoc(String uN,String pass) {
		String loginS="";
		loginS =sqlC.ACCOUNT_BANDOC_DANGNHAP;
		BANDOC bD = new BANDOC();
		try {
			pstmt = jdbc.getConnect().prepareStatement(loginS);
			pstmt.setString(1, uN);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bD.setMaBanDoc(rs.getInt("MABANDOC"));
				bD.setGioiTinh(rs.getString("GIOITINH"));
				bD.setDiaChi(rs.getString("DIACHI"));
				bD.setNgayGiaHan(rs.getDate("NGAYGIAHAN"));
				bD.setNgayHetHan(rs.getDate("NGAYHETHAN"));
				bD.setTenBanDoc(rs.getString("TENBANDOC"));
				bD.setUserName(rs.getString("USERNAME"));
				bD.setNgaySinh(rs.getDate("NGAYSINH"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				jdbc.releaseResource(rs, stmt, pstmt, null, null);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return bD;
	}
	
	public THUTHU checkLoginThuThu(String uN,String pass) {
		String loginS =sqlC.ACCOUNT_THUTHU_DANGNHAP;
		THUTHU bD = new THUTHU();
		try {
			pstmt = jdbc.getConnect().prepareStatement(loginS);
			pstmt.setString(1, uN);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bD.setMaThuThu(rs.getInt("MATHUTHU"));
				bD.setTenThuThu(rs.getString("TENTHUTHU"));
				bD.setDiaChi(rs.getString("DIACHI"));
				bD.setSDT(rs.getString("SDT"));
				bD.setUserName(rs.getString("USERNAME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				jdbc.releaseResource(rs, stmt, pstmt, null, null);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return bD;
	}
	
	public boolean doiMK(String uN,String pass) {
		boolean check = true;
		try {
			String str = sqlC.ACCOUNT_DOIMATKHAU;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setString(1, pass);
			pstmt.setString(2,uN);
			pstmt.execute();
		} catch (Exception e) {
			check = false;
			e.printStackTrace();
		}finally {
			try {
				jdbc.releaseResource(rs, stmt, pstmt, null, null);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return check;
	}
	
}

