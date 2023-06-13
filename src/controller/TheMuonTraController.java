package controller;

import java.sql.Date;
import java.util.ArrayList;
import model.*;
import sql.ConnectSQL;

public class TheMuonTraController extends Controller{

	public TheMuonTraController(ConnectSQL c) {
		super(c);
		
	}
	
	public THEMUONTRA timTheMuonTra(THEMUONTRA t) {
		THEMUONTRA l = new THEMUONTRA();
		try {
			String str = sqlC.THEMUONTRA_TIM_MA;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, t.getMaMuonTra());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer mamt = rs.getInt("MAMUONTRA");
				Integer mas = rs.getInt("MASACH");
				Integer mabd = rs.getInt("MABANDOC");
				Integer matt = rs.getInt("MATHUTHU");
				Date nm = rs.getDate("NGAYMUON");
				Date nt = rs.getDate("NGAYTRA");
				boolean dangmuon = rs.getBoolean("DANGMUON");
				float tienCoc = rs.getFloat("TIENCOC");
				String ghiChu = rs.getString("GHICHU");
				float tienPhat = rs.getFloat("TIENPHAT");
				BANDOC b = new BANDOC();
				b.setMaBanDoc(mabd);
				
				SACH s = new SACH();
				s.setMaSach(mas);
				
				THUTHU tt = new THUTHU();
				tt.setMaThuThu(matt);
				
				l.setMaMuonTra(mamt);
				l.setNgayMuon(nm);
				l.setNgayTra(nt);
				l.setGhiChu(ghiChu);
				l.setDangMuon(dangmuon);
				l.setSach(s);
				l.setBanDoc(b);
				l.setThuThu(tt);
				l.setTienCoc(tienCoc);
				l.setTienPhat(tienPhat);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				jdbc.releaseResource(rs, stmt, pstmt, null, null);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		l.setSach(timSach(l.getSach()));
		l.setBanDoc(timBanDoc(l.getBanDoc()));
		l.setThuThu(timThuThu(l.getThuThu()));
		return l;
	}
	
	public ArrayList<THEMUONTRA> xemDS_BanDoc(BANDOC t){
		ArrayList<THEMUONTRA> l = new ArrayList<>();
		try {
			String str = sqlC.THEMUONTRA_TIM_BANDOC;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, t.getMaBanDoc());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer mamt = rs.getInt("MAMUONTRA");
				Integer mas = rs.getInt("MASACH");
				Date nm = rs.getDate("NGAYMUON");
				Date nt = rs.getDate("NGAYTRA");
				boolean dangmuon = rs.getBoolean("DANGMUON");
				
				SACH s = new SACH();
				s.setMaSach(mas);
				
				THEMUONTRA tmt = new THEMUONTRA();
				tmt.setMaMuonTra(mamt);
				tmt.setNgayMuon(nm);
				tmt.setNgayTra(nt);
				tmt.setDangMuon(dangmuon);
				tmt.setSach(s);
				
				l.add(tmt);
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				jdbc.releaseResource(rs, stmt, pstmt, null, null);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		for (int i = 0; i < l.size(); i++) {
			l.get(i).setSach(timSach(l.get(i).getSach()));
		}
		return l;
	}
	
	public ArrayList<THEMUONTRA> xemtt(){
		ArrayList<THEMUONTRA> l = new ArrayList<>();
		try {
			String str = sqlC.THEMUONTRA_XEMTT;
			pstmt = jdbc.getConnect().prepareStatement(str);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer mamt = rs.getInt("MAMUONTRA");
				Integer mas = rs.getInt("MASACH");
				Date nm = rs.getDate("NGAYMUON");
				Date nt = rs.getDate("NGAYTRA");
				boolean dangmuon = rs.getBoolean("DANGMUON");
				
				
				SACH s = new SACH();
				s.setMaSach(mas);
				
				THEMUONTRA tmt = new THEMUONTRA();
				tmt.setMaMuonTra(mamt);
				tmt.setNgayMuon(nm);
				tmt.setNgayTra(nt);
				tmt.setDangMuon(dangmuon);
				tmt.setSach(s);
				
				l.add(tmt);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				jdbc.releaseResource(rs, stmt, pstmt, null, null);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		for (int i = 0; i < l.size(); i++) {
			l.get(i).setSach(timSach(l.get(i).getSach()));
		}
		return l;
	}
	
	public boolean xoa(THEMUONTRA t) {
		boolean check = true;
		try {
			String str = sqlC.THEMUONTRA_XOA_MA;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, t.getMaMuonTra());
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			check=false;
		}finally {
			try {
				jdbc.releaseResource(rs, stmt, pstmt, null, null);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return check;
	}
	
	public boolean them(THEMUONTRA t) {
		boolean check = true;
		try {
			String str = sqlC.THEMUONTRA_THEM;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setDate(1, t.getNgayMuon());
			pstmt.setDate(2, t.getNgayTra());
			pstmt.setString(3, t.getGhiChu());
			pstmt.setFloat(4, t.getTienCoc());
			pstmt.setInt(5, t.getThuThu().getMaThuThu());
			pstmt.setInt(6, t.getBanDoc().getMaBanDoc());
			pstmt.setInt(7, t.getSach().getMaSach());
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			check=false;
		}finally {
			try {
				jdbc.releaseResource(rs, stmt, pstmt, null, null);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return check;
	}
	
	public boolean traSach(THEMUONTRA t) {
		boolean check = true;
		try {
			String str = sqlC.THEMUONTRA_TRASACH;
			pstmt = jdbc.getConnect().prepareStatement(str);
			
			pstmt.setString(1, t.getGhiChu());
			pstmt.setFloat(2, t.getTienPhat());
			pstmt.setInt(3, t.getMaMuonTra());
			
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			check=false;
		}finally {
			try {
				jdbc.releaseResource(rs, stmt, pstmt, null, null);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return check;
	}

	public ArrayList<THEMUONTRA> timKiem(String tkS) {
		ArrayList<THEMUONTRA> l = new ArrayList<>();
		boolean check = true;
		try {
			String str = sqlC.THEMUONTRA_TIM_SACH;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setString(1, "%"+tkS+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer mamt = rs.getInt("MAMUONTRA");
				Integer mas = rs.getInt("MASACH");
				Date nm = rs.getDate("NGAYMUON");
				Date nt = rs.getDate("NGAYTRA");
				boolean dangmuon = rs.getBoolean("DANGMUON");
				
				
				SACH s = new SACH();
				s.setMaSach(mas);
				
				THEMUONTRA tmt = new THEMUONTRA();
				tmt.setMaMuonTra(mamt);
				tmt.setNgayMuon(nm);
				tmt.setNgayTra(nt);
				tmt.setDangMuon(dangmuon);
				tmt.setSach(s);
				
				l.add(tmt);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			check=false;
		}finally {
			try {
				jdbc.releaseResource(rs, stmt, pstmt, null, null);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		for (int i = 0; i < l.size(); i++) {
			l.get(i).setSach(timSach(l.get(i).getSach()));
		}
		return l;
	}

}
