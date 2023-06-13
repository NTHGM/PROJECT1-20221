package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.BANDOC;
import model.NXB;
import model.SACH;
import model.TACGIA;
import model.THELOAI;
import model.THUTHU;
import sql.ConnectSQL;
import sql.SQLCommand;

public class Controller {
	ConnectSQL jdbc; 
	ResultSet rs;
	PreparedStatement pstmt;
	Statement stmt;
	SQLCommand sqlC;
	
	public Controller(ConnectSQL c) {
		jdbc = c;
		sqlC = new SQLCommand();
	}
	
	public ConnectSQL getConnect() {
		return jdbc;
	}
	
	public THUTHU timThuThu(THUTHU l) {
		THUTHU t = new THUTHU();
		try {
			String str = sqlC.THUTHU_TIM_MA;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, l.getMaThuThu());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer matt = rs.getInt("MATHUTHU");
				String ten = rs.getString("TENTHUTHU");
				String dc = rs.getString("DIACHI");
				String sdt = rs.getString("SDT");
				String uN  = rs.getString("USERNAME");
				
				t.setMaThuThu(matt);
				t.setDiaChi(dc);
				t.setTenThuThu(ten);
				t.setSDT(sdt);
				t.setUserName(uN);
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
		return t;
	}
	
	public BANDOC timBanDoc(BANDOC bb) {
		BANDOC l = new BANDOC();
		String str = sqlC.BANDOC_TIMKIEM_MA;
		try {
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, bb.getMaBanDoc());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer maBD = rs.getInt("MABANDOC");
				String tenBD = rs.getString("TENBANDOC");
				String gioiTinh = rs.getString("GIOITINH");
				String diaChi = rs.getString("DIACHI");
				Date ngaySinh = rs.getDate("NGAYSINH");
				Date ngh = rs.getDate("NGAYGIAHAN");
				Date nhh = rs.getDate("NGAYHETHAN");
				String uN = rs.getString("USERNAME");
				
				l = new BANDOC(maBD, tenBD, gioiTinh, diaChi, ngaySinh,ngh,nhh);
				l.setUserName(uN);
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
	
	public SACH timSach(SACH s) {
		ArrayList<SACH> l = new ArrayList<>();
 		String str = sqlC.SACH_TIM_MA;
		try {
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, s.getMaSach());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer masach = rs.getInt("MASACH");
				String tensach = rs.getString("TENSACH");
				int namxb = rs.getInt("NAMXUATBAN");
				Integer mNXB = rs.getInt("MANXB");
				String nxb = rs.getString("TENNXB");
				String dchi = rs.getString("DIACHI");
				Integer tlM = rs.getInt("MATHELOAI");
				String tl = rs.getString("TENTHELOAI");
				Integer matg = rs.getInt("MATACGIA");
				String tentg = rs.getString("TENTACGIA");
				int solg = rs.getInt("SOLUONG");
				TACGIA tg  = new TACGIA(matg,tentg);
				NXB n = new NXB(mNXB, nxb, dchi);
				THELOAI t = new THELOAI(tlM, tl);
				ArrayList<TACGIA> tL = new ArrayList<>();
				tL.add(tg);

				SACH tmp = new SACH(masach, tensach, namxb,t, n,solg);
				tmp.setTacgiaL(tL);
				l.add(tmp);
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
		
		for (int i = 1; i < l.size(); i++) {
			l.get(0).addTacgia(l.get(i).getTacgiaL().get(0));
		}
		if(l.get(0)==null) return new SACH();
		return l.get(0);
		
	}
	
	public boolean xoaSach(SACH s) {
		boolean check = false;
		try {
			String str2 = sqlC.SACH_XOA;
			pstmt = jdbc.getConnect().prepareStatement(str2);
			pstmt.setInt(1, s.getMaSach());
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
	
	public boolean xoaSach_TheMuonTra(SACH s){
		boolean check = true;
		try {
			String str = sqlC.THEMUONTRA_XOA_SACH;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, s.getMaSach());
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
	
	public boolean xoaTS_maSach(SACH s){
		boolean check = true;
		try {
			String str = sqlC.TS_XOA_MASACH;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, s.getMaSach());
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
	
	public boolean xoaTS_maTacGia(TACGIA s){
		boolean check = true;
		try {
			String str = sqlC.TS_XOA_MATACGIA;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, s.getMaTacGia());
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
	
	public boolean xoaSach_NXB(NXB n) {
		boolean check = true;
		try {
			String str = sqlC.NXB_SETNULL;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, n.getMaNXB());
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
	public boolean xoaSach_TheLoai(THELOAI t) {
		boolean check = true;
		try {
			String str = sqlC.THELOAI_SETNULL;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, t.getMaTheLoai());
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
	
	//BANDOC
	
	public boolean xoaBanDoc(BANDOC b) {
		boolean check = true;
		try {
			String str = sqlC.THEMUONTRA_XOA_BANDOC;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, b.getMaBanDoc());
			pstmt.execute();
			jdbc.releaseResource(rs, stmt, pstmt, null, null);
			
			str = sqlC.BANDOC_XOA;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, b.getMaBanDoc());
			pstmt.execute();
			jdbc.releaseResource(rs, stmt, pstmt, null, null);
			
			str = sqlC.ACCOUNT_DELETE;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setString(1,b.getUserName());
			pstmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
			check =false;
			e.printStackTrace();
		}finally {
			try {
				jdbc.releaseResource(rs, stmt, pstmt, null, null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return check;
	}
}
