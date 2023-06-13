package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import model.BANDOC;
import model.SACH;
import model.TACGIA;
import sql.ConnectSQL;

public class TacGiaController extends Controller{

	public TacGiaController(ConnectSQL c) {
		super(c);
	}
	
	public TACGIA timTacGia(TACGIA t) {
		TACGIA l = new TACGIA();
		try {
			String str = sqlC.TACGIA_TIM_MA;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, t.getMaTacGia());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				l.setMaTacGia(rs.getInt("MATACGIA"));
				l.setTenTacGia(rs.getString("TENTACGIA"));
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
	
	public ArrayList<TACGIA> xemTT() {
		ArrayList<TACGIA> a = new ArrayList<>();
		String xemttS = sqlC.TACGIA_XEMTT;
		try {
			pstmt = jdbc.getConnect().prepareStatement(xemttS);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer maTG = rs.getInt("MATACGIA");
				String tenTG = rs.getString("TENTACGIA");
				Integer solg = rs.getInt("SOLUONGSACH");
				TACGIA tG = new TACGIA(maTG,tenTG,solg);
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
	
	
	public boolean themTG(TACGIA tg) {
		boolean ok = false;
		String str = sqlC.TACGIA_THEMTACGIA;
		try {
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setString(1, tg.getTenTacGia());
			pstmt.execute();
			ok = true;
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				jdbc.releaseResource(rs, stmt, pstmt, null, null);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return ok;
	}
	
	public boolean suaTG(TACGIA t) {
		boolean ok = false;
		String srt = sqlC.TACGIA_SUATACGIA;
		try {
			pstmt = jdbc.getConnect().prepareStatement(srt);
			pstmt.setString(1, t.getTenTacGia());
			pstmt.setInt(2, t.getMaTacGia());
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
	
	public boolean xoaTG(TACGIA t) {
		boolean check = false;
		String str = sqlC.TACGIA_XOATACGIA;
		try {
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, t.getMaTacGia());
			pstmt.execute();
			check=true;
		} catch (Exception e) {
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

	public ArrayList<TACGIA> timKiem(String tkS) {
		ArrayList<TACGIA> a = new ArrayList<>();
		String str = sqlC.TACGIA_TIM_TEN;
		try {
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setString(1, "%"+tkS+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Integer maTG = rs.getInt("MATACGIA");
				String tenTG = rs.getString("TENTACGIA");
				Integer solg = rs.getInt("SOLUONGSACH");
				TACGIA tG = new TACGIA(maTG,tenTG,solg);
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
