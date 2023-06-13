package model;

import java.util.ArrayList;

public class SACH {
	private Integer maSach;
	private String tenSach;
	private Integer namXuatBan;
	private THELOAI theLoai;
	private NXB NXB;
	private ArrayList<TACGIA> tacgiaL = new ArrayList<>();
	private int solg;
	int soSachMuon;
	
	public SACH() {}
	
	public SACH(Integer maSach, String tenSach, THELOAI theLoai, model.NXB nXB) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.theLoai = theLoai;
		NXB = nXB;
	}

	public SACH(Integer maSach, String tenSach, Integer namXuatBan, THELOAI theLoai, model.NXB nXB,
			ArrayList<TACGIA> tacgiaL, Integer solg) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.namXuatBan = namXuatBan;
		this.theLoai = theLoai;
		NXB = nXB;
		this.tacgiaL = tacgiaL;
		this.solg = solg;
	}



	public SACH(String tenSach, Integer namXuatBan, THELOAI theLoai, NXB nXB, ArrayList<TACGIA> tacgiaL,
			Integer solg) {
		super();
		this.tenSach = tenSach;
		this.namXuatBan = namXuatBan;
		this.theLoai = theLoai;
		NXB = nXB;
		this.tacgiaL = tacgiaL;
		this.solg = solg;
	}



	public SACH(Integer maSach, String tenSach, Integer namXuatBan, NXB nXB,THELOAI theLoai, Integer solg) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.namXuatBan = namXuatBan;
		this.theLoai = theLoai;
		NXB = nXB;
		this.solg=solg;
	}

	
	public SACH(Integer maSach, String tenSach, Integer namXuatBan, THELOAI theLoai,NXB nXB,int solg) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.namXuatBan = namXuatBan;
		this.theLoai = theLoai;
		NXB = nXB;
		this.solg=solg;
	}

	public String getTenNXB() {
		if(NXB.getMaNXB()==0) return "Chưa rõ";
		return NXB.getTenNXB();
	}

	public void setNXB(NXB nXB) {
		NXB = nXB;
	}
	
	public NXB getNXB() {
		return NXB;
	}

	public void addTacgia(TACGIA t) {
		tacgiaL.add(t);
	}

	public Integer getMaSach() {
		return maSach;
	}

	public void setMaSach(int maSach) {
		this.maSach = maSach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public Integer getNamXuatBan() {
		return namXuatBan;
	}

	public void setNamXuatBan(Integer namXuatBan) {
		this.namXuatBan = namXuatBan;
	}

	public THELOAI getTheLoai() {
		return theLoai;
	}
	
	public String getTenTheLoai() {
		if(theLoai.getMaTheLoai()==0) return "Chưa rõ";
		return theLoai.getTenTheLoai();
	}

	public void setTheLoai(THELOAI theLoai) {
		this.theLoai = theLoai;
	}

	public ArrayList<TACGIA> getTacgiaL() {
		if(tacgiaL.get(0).getMaTacGia()==0) tacgiaL.remove(0);
		return tacgiaL;
	}
	
	public String getTenTacgiaL() {
		String tenTG="";
		for(int i=0;i<tacgiaL.size();i++) {
			if(i!=(tacgiaL.size()-1))tenTG+=tacgiaL.get(i).getTenTacGia()+", ";
			else tenTG+=tacgiaL.get(i).getTenTacGia();
		}
		if(tenTG.equals("null")) return "Chưa rõ";
		return tenTG;
	}

	public void setTacgiaL(ArrayList<TACGIA> tacgiaL) {
		this.tacgiaL = tacgiaL;
	}

	public int getSolg() {
		return solg;
	}

	public void setSolg(int solg) {
		this.solg = solg;
	}

	public void setMaSach(Integer maSach) {
		this.maSach = maSach;
	}

	public int getSoSachMuon() {
		return soSachMuon;
	}

	public void setSoSachMuon(int soSachMuon) {
		this.soSachMuon = soSachMuon;
	}
	
	
	
}
