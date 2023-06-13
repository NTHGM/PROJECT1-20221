package controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import model.BANDOC;

import model.THELOAI;
import sql.ConnectSQL;

public class BanDocController extends Controller{

	public BanDocController(ConnectSQL c) {
		super(c);
		// TODO Auto-generated constructor stub
	}
	//BANDOC
	public ArrayList<BANDOC> xemtt(){
		ArrayList<BANDOC> a = new ArrayList<>();
		String xemttS = sqlC.BANDOC_XEMTT;
		try {
			stmt = jdbc.getConnect().createStatement();
			rs = stmt.executeQuery(xemttS);
			while(rs.next()) {
				Integer maBD = rs.getInt("MABANDOC");
				String tenBD = rs.getString("TENBANDOC");
				String gioiTinh = rs.getString("GIOITINH");
				String diaChi = rs.getString("DIACHI");
				Date ngaySinh = rs.getDate("NGAYSINH");
				Date ngh = rs.getDate("NGAYGIAHAN");
				Date nhh = rs.getDate("NGAYHETHAN");
				String uN = rs.getString("USERNAME");
				
				BANDOC bD = new BANDOC(maBD, tenBD, gioiTinh, diaChi, ngaySinh,ngh,nhh);
				bD.setUserName(uN);
				a.add(bD);
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
	public boolean themBanDoc(BANDOC b, String mk) {
		boolean check = true;
		String dk1 = sqlC.ACCOUNT_BANDOC_DANGKI;
		String dk2 = sqlC.BANDOC_THEMBANDOC;
		
		try {
			pstmt = jdbc.getConnect().prepareStatement(dk1);
			pstmt.setString(1, b.getUserName());
			pstmt.setString(2, mk);
			pstmt.execute();
			
			jdbc.releaseResource(rs, stmt, pstmt, null, null);
			pstmt = jdbc.getConnect().prepareStatement(dk2);
			pstmt.setString(1,b.getTenBanDoc());
			pstmt.setDate(2, b.getNgaySinh());
			pstmt.setString(3, b.getGioiTinh());
			pstmt.setString(4, b.getDiaChi());
			pstmt.setString(5, b.getUserName());
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
	
	public boolean suaBanDoc(BANDOC b) {
		boolean check = true;
		try {
			String str = sqlC.BANDOC_SUABANDOC;
			jdbc.releaseResource(rs, stmt, pstmt, null, null);
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setString(1,b.getTenBanDoc());
			pstmt.setDate(2, b.getNgaySinh());
			pstmt.setString(3, b.getGioiTinh());
			pstmt.setString(4, b.getDiaChi());
			pstmt.setInt(5, b.getMaBanDoc());
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
	
	public boolean giaHan(BANDOC b) {
		boolean check = true;
		try {
			String str = sqlC.BANDOC_GIAHAN;
			jdbc.releaseResource(rs, stmt, pstmt, null, null);
			pstmt = jdbc.getConnect().prepareStatement(str);
			
			pstmt.setDate(1,b.getNgayGiaHan());
			pstmt.setDate(2, b.getNgayHetHan());
			pstmt.setInt(3, b.getMaBanDoc());
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
	public ArrayList<BANDOC> timKiem(String tkS) {
		ArrayList<BANDOC> l = new ArrayList<>();
		try {
			String str = sqlC.BANDOC_TIMKIEM_TEN;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setString(1, "%"+tkS+"%");
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
				
				BANDOC bD = new BANDOC(maBD, tenBD, gioiTinh, diaChi, ngaySinh,ngh,nhh);
				bD.setUserName(uN);
				l.add(bD);
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
		
		return l;
	}
	
	
}
