package controller;

import java.util.ArrayList;

import model.SACH;
import model.TACGIA;
import model.THELOAI;
import sql.ConnectSQL;

public class TheLoaiController extends Controller{

	public TheLoaiController(ConnectSQL c) {
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	public THELOAI timTheLoai(THELOAI t) {
		THELOAI l = new THELOAI();
		try {
			String str = sqlC.THELOAI_TIM_MA;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, t.getMaTheLoai());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				l.setMaTheLoai(rs.getInt("MATHELOAI"));
				l.setTenTheLoai(rs.getString("TENTHELOAI"));
				l.setSl(rs.getInt("SOLUONGSACH"));
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
		return l;
	}
	
	public boolean themTheLoai(THELOAI t) {
		boolean check = true;
		String str = sqlC.THELOAI_THEM;
		try {
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setString(1, t.getTenTheLoai());
			pstmt.execute();
			
		} catch (Exception e) {
			check=false;
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
	public ArrayList<THELOAI> xemTT() {
		ArrayList<THELOAI> a = new ArrayList<>();
		String xemttS = sqlC.THELOAI_XEMTT;
		try {
			pstmt = jdbc.getConnect().prepareStatement(xemttS);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer ma = rs.getInt("MATHELOAI");
				String ten = rs.getString("TENTHELOAI");
				Integer solg = rs.getInt("SOLUONGSACH");
				THELOAI t = new THELOAI(ma,ten,solg);
				a.add(t);
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
		return a;
	}
		
	public boolean suaTL(THELOAI t) {
		boolean ok = false;
		String srt = sqlC.THELOAI_SUA;
		try {
			pstmt = jdbc.getConnect().prepareStatement(srt);
			pstmt.setString(1, t.getTenTheLoai());
			pstmt.setInt(2, t.getMaTheLoai());
			pstmt.execute();
			ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				jdbc.releaseResource(rs, stmt, pstmt, null, null);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return ok;
	}
	
	public boolean xoaTL(THELOAI t) {
		boolean check = false;
		String str = sqlC.THELOAI_XOA;
		try {
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, t.getMaTheLoai());
			pstmt.execute();
			check=true;
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				jdbc.releaseResource(rs, stmt, pstmt, null, null);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return check;
	}
	
	public ArrayList<THELOAI> timKiem(String tkS) {
		ArrayList<THELOAI> a = new ArrayList<>();
		String str = sqlC.THELOAI_TIM_TEN;
		try {
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setString(1, "%"+tkS+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Integer maTG = rs.getInt("MATHELOAI");
				String tenTG = rs.getString("TENTHELOAI");
				Integer solg = rs.getInt("SOLUONGSACH");
				THELOAI tG = new THELOAI(maTG,tenTG,solg);
				a.add(tG);
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
		return a;
	}
	
	
}
