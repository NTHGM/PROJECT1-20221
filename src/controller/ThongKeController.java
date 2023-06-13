package controller;

import sql.ConnectSQL;

public class ThongKeController extends Controller{

	public ThongKeController(ConnectSQL c) {
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	public int tongSoSach() {
		int a = 0;
		try {
			String str = sqlC.TK_TONGSACH;
			pstmt = jdbc.getConnect().prepareStatement(str);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				a = rs.getInt(("TONG"));
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
	
	public int tongSoTheLoai() {
		int a = 0;
		try {
			String str = sqlC.TK_TONGTL;
			pstmt = jdbc.getConnect().prepareStatement(str);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				a = rs.getInt(("TONG"));
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
	public int tongSoNXB() {
		int a = 0;
		try {
			String str = sqlC.TK_TONGNXB;
			pstmt = jdbc.getConnect().prepareStatement(str);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				a = rs.getInt(("TONG"));
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
	
	public int tongSoTacGia() {
		int a = 0;
		try {
			String str = sqlC.TK_TONGTG;
			pstmt = jdbc.getConnect().prepareStatement(str);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				a = rs.getInt(("TONG"));
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
	
	public int tongSoBanDoc() {
		int a = 0;
		try {
			String str = sqlC.TK_TONGBD;
			pstmt = jdbc.getConnect().prepareStatement(str);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				a = rs.getInt(("TONG"));
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
	public int tongSoNam() {
		int a = 0;
		try {
			String str = sqlC.TK_TONGNAM;
			pstmt = jdbc.getConnect().prepareStatement(str);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				a = rs.getInt(("TONG"));
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
	public int tongSoNu() {
		int a = 0;
		try {
			String str = sqlC.TK_TONGNU;
			pstmt = jdbc.getConnect().prepareStatement(str);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				a = rs.getInt(("TONG"));
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
	
	public int tongSoKhac() {
		int a = 0;
		try {
			String str = sqlC.TK_TONGKHAC;
			pstmt = jdbc.getConnect().prepareStatement(str);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				a = rs.getInt(("TONG"));
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
	public int tongDuoi18() {
		int a = 0;
		try {
			String str = sqlC.TK_TONGDUOI18;
			pstmt = jdbc.getConnect().prepareStatement(str);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				a = rs.getInt(("TONG"));
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
	public int tong18() {
		int a = 0;
		try {
			String str = sqlC.TK_TONG18;
			pstmt = jdbc.getConnect().prepareStatement(str);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				a = rs.getInt(("TONG"));
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
	
	public int tongMuonTra() {
		int a = 0;
		try {
			String str = sqlC.TK_TONGTMT;
			pstmt = jdbc.getConnect().prepareStatement(str);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				a = rs.getInt(("TONG"));
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
	
	public int tongDaTra() {
		int a = 0;
		try {
			String str = sqlC.TK_TONGDATRA;
			pstmt = jdbc.getConnect().prepareStatement(str);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				a = rs.getInt(("TONG"));
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
	
	public int tongDangMuon() {
		int a = 0;
		try {
			String str = sqlC.TK_TONGCHUATRA;
			pstmt = jdbc.getConnect().prepareStatement(str);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				a = rs.getInt(("TONG"));
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
	public int tongQuaHan() {
		int a = 0;
		try {
			String str = sqlC.TK_TONGQUAHAN;
			pstmt = jdbc.getConnect().prepareStatement(str);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				a = rs.getInt(("TONG"));
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
