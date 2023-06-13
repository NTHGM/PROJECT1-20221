package controller;

import java.util.ArrayList;

import model.*;

import sql.ConnectSQL;

public class SachController extends Controller{

	public SachController(ConnectSQL c) {
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	
	//SACH
	
	
	
	public boolean themS(SACH s) {
		boolean check = true;
		try {
			String str = sqlC.SACH_THEMSACH;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setString(1, s.getTenSach());
			pstmt.setInt(2, s.getNamXuatBan());
			pstmt.setInt(3, s.getNXB().getMaNXB());
			pstmt.setInt(4, s.getTheLoai().getMaTheLoai());
			pstmt.execute();
			jdbc.releaseResource(rs, stmt, pstmt, null, null);
			
			s.setMaSach(getLastInserted());
			
			themTS(s);
		} catch (Exception e) {
			check = false;
			e.printStackTrace();
		}finally {
			try {
				jdbc.releaseResource(rs, stmt, pstmt, null, null);
			} catch (Exception e2) {
				
			}
		}
		return check;
	}
	
	public ArrayList<SACH> xemTT(){
		ArrayList<SACH> tmp = new ArrayList<>();
		String str = sqlC.SACH_XEMTT;
		try {
			pstmt = jdbc.getConnect().prepareStatement(str);
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

				SACH s = new SACH(masach, tensach, namxb,t, n,solg);
				s.setTacgiaL(tL);
				tmp.add(s);
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
		for (int i = 0; i < tmp.size()-1; i++) {
			for (int j = i+1; j < tmp.size(); j++) {
				if(tmp.get(i).getMaSach()==tmp.get(j).getMaSach()) {
					tmp.get(i).addTacgia(tmp.get(j).getTacgiaL().get(0));
					tmp.remove(j);
					j--;
				}
			}
		}
		
		return tmp;
	}
	
	public boolean suaS(SACH s) {
		boolean check = true;
		try {
			String str = sqlC.SACH_SUA;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setString(1, s.getTenSach());
			pstmt.setInt(2, s.getNamXuatBan());
			Integer maNXB = null;
			if(s.getNXB().getMaNXB()!=0) maNXB = s.getNXB().getMaNXB();
			Integer maTL = null;
			if(s.getTheLoai().getMaTheLoai()!=0) maTL = s.getTheLoai().getMaTheLoai();
			pstmt.setInt(3, maNXB);
			pstmt.setInt(4, maTL);
			pstmt.setInt(5, s.getMaSach());
			pstmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
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
	
	
	
	
	
	public Integer getLastInserted() {
		int a = -1;
		try {
			String str1 = sqlC.SACH_LAST_INSERT;
			pstmt = jdbc.getConnect().prepareStatement(str1);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				a = (rs.getInt("MASACH"));
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
	//TACGIA-SACH
	public ArrayList<TACGIA> list_tacgia(SACH s){
		ArrayList<TACGIA> l = new ArrayList<>();
		String str = sqlC.TS_LIST_TACGIA;
		try {
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, s.getMaSach());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer maTG = rs.getInt("MATACGIA");
				String tenTG = rs.getString("TENTACGIA");
				Integer solg = rs.getInt("SOLUONGSACH");
				TACGIA tG = new TACGIA(maTG,tenTG,solg);
				l.add(tG);
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
	
	public boolean themTS(SACH s) {
		boolean check=true;
		try {
			String str3 = sqlC.TS_THEM;
			pstmt = jdbc.getConnect().prepareStatement(str3);
			pstmt.setInt(1, s.getMaSach());
			for(int i=0;i<s.getTacgiaL().size();i++) {
				pstmt.setInt(2, s.getTacgiaL().get(i).getMaTacGia());
				pstmt.execute();
			}
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
	
	public boolean xoaTS(Integer masach,Integer matg){
		boolean check = true;
		try {
			String str = sqlC.TS_XOA;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, masach);
			pstmt.setInt(2, matg);
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
	
	public int soSachMuon(SACH s) {
		int so = 0;
		try {
			String str = sqlC.SACH_SOSACHMUON;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, s.getMaSach());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				so++;
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
		return so;
	}


	public ArrayList<SACH> timKiem(String tkS) {
		ArrayList<SACH> tmp = new ArrayList<>();
		try {
			String str = sqlC.SACH_TIM_NXB;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setString(1, "%"+tkS+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer masach = rs.getInt("MASACH");
				SACH s = new SACH();
				s.setMaSach(masach);;
				tmp.add(s);
			}
			jdbc.releaseResource(rs, stmt, pstmt, null, null);
			str = sqlC.SACH_TIM_TEN;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setString(1, "%"+tkS+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer masach = rs.getInt("MASACH");
				SACH s = new SACH();
				s.setMaSach(masach);;
				tmp.add(s);
			}
			jdbc.releaseResource(rs, stmt, pstmt, null, null);
			str = sqlC.SACH_TIM_THELOAI;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setString(1, "%"+tkS+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer masach = rs.getInt("MASACH");
				SACH s = new SACH();
				s.setMaSach(masach);;
				tmp.add(s);
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
		for(int i = 0;i<tmp.size()-1;i++) {
			for (int j = i+1; j < tmp.size(); j++) {
				if(tmp.get(i).getMaSach()==tmp.get(j).getMaSach()) {
					tmp.remove(j);
					j--;
				}
			}
		}
		ArrayList<SACH> l =new ArrayList<>();
		for (int i = 0; i < tmp.size(); i++) {
			l.add(timSach(tmp.get(i)));
		}
		
		ArrayList<SACH> stL = timKiemSach_TacGia(tkS);
		
		for(int i = 0;i<stL.size();i++) {
			l.add(stL.get(i));
		}
		
		for(int i = 0;i<l.size()-1;i++) {
			for (int j = i+1; j < l.size(); j++) {
				if(l.get(i).getMaSach()==l.get(j).getMaSach()) {
					l.remove(j);
					j--;
				}
			}
		}
		
		return l;
	}
	public ArrayList<SACH> timKiemSach_TacGia(String tkS){
		ArrayList<SACH> tmp = new ArrayList<>();
		try {
			String str = sqlC.SACH_TIM_TACGIA;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setString(1, "%"+tkS+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int masach = rs.getInt("MASACH");
				SACH s = new SACH();
				s.setMaSach(masach);
				tmp.add(s);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				jdbc.releaseResource(rs, stmt, pstmt, null, null);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		ArrayList<SACH> l = new ArrayList<>();
		for (int i = 0; i < tmp.size(); i++) {
			SACH ts = tmp.get(i);
			l.add(timSach(ts));
		}
		return l;
	}


	public void themSl(SACH s) {
		try {
			String str =sqlC.SACH_THEMSOLUONG;
			pstmt = jdbc.getConnect().prepareStatement(str);
			pstmt.setInt(1, s.getSolg());
			pstmt.setInt(2, s.getMaSach());
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				jdbc.releaseResource(rs, stmt, pstmt, null, null);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}

}
