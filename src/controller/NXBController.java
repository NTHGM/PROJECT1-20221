package controller;

import java.lang.reflect.Array;
import java.util.ArrayList;

import model.NXB;
import model.SACH;
import model.TACGIA;
import model.THELOAI;
import sql.ConnectSQL;

public class NXBController extends Controller{

	public NXBController(ConnectSQL c) {
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	public NXB timNXB(NXB n) {
		NXB l = new NXB();
		try {
		String str = sqlC.NXB_TIM_MA;
		pstmt = jdbc.getConnect().prepareStatement(str);
		pstmt.setInt(1, n.getMaNXB());
		rs = pstmt.executeQuery();
		while(rs.next()) {
			l.setMaNXB(rs.getInt("MANXB"));
			l.setTenNXB(rs.getString("TENNXB"));
			l.setSl(rs.getInt("SOLUONGSACH"));
			l.setDiaChi(rs.getString("DIACHI"));
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
	
	public boolean themNXB(NXB n) {
		boolean check = true;
		String str = sqlC.NXB_THEM;
		try {
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setString(1, n.getTenNXB());
			pstmt.setString(2, n.getDiaChi());
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
	public ArrayList<NXB> xemTT() {
		ArrayList<NXB> a = new ArrayList<>();
		String xemttS = sqlC.NXB_XEMTT;
		try {
			pstmt = jdbc.getConnect().prepareStatement(xemttS);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer ma = rs.getInt("MANXB");
				String ten = rs.getString("TENNXB");
				Integer solg = rs.getInt("SOLUONGSACH");
				String dc = rs.getString("DIACHI");
				NXB t = new NXB(ma,ten,dc,solg);
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
		
	public boolean suaNXB(NXB t) {
		boolean ok = false;
		String srt = sqlC.NXB_SUA;
		try {
			pstmt = jdbc.getConnect().prepareStatement(srt);
			pstmt.setString(1, t.getTenNXB());
			pstmt.setString(2, t.getDiaChi());
			pstmt.setInt(3, t.getMaNXB());
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
	
	public boolean xoaNXB(NXB n) {
		boolean check = false;
		String str = sqlC.NXB_XOA;
		try {
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, n.getMaNXB());
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
	
	public ArrayList<NXB> timKiem(String tkS){
		ArrayList<NXB> l = new ArrayList<>();
		try {
			String str = sqlC.NXB_TIM_TEN;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setString(1, "%"+tkS+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer ma = rs.getInt("MANXB");
				String ten = rs.getString("TENNXB");
				Integer solg = rs.getInt("SOLUONGSACH");
				String dc = rs.getString("DIACHI");
				NXB t = new NXB(ma,ten,dc,solg);
				l.add(t);
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
	

}
