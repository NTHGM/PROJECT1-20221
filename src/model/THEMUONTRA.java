package model;

import java.sql.Date;

public class THEMUONTRA {
	private Integer maMuonTra;
	private Date ngayMuon;
	private Date ngayTra;
	private String ghiChu;
	private float tienCoc;
	private boolean dangmuon;
	private THUTHU thuThu;
	BANDOC banDoc;
	private SACH sach;
	float tienPhat;
	
	public float getTienPhat() {
		return tienPhat;
	}
	public String getStringTienPhat() {
		if(tienPhat==0) return "Chưa trả sách"; 
		return tienPhat+"";
	}

	public void setTienPhat(float tienPhat) {
		this.tienPhat = tienPhat;
	}

	public THEMUONTRA() {}

	public THEMUONTRA(Integer maMuonTra, Date bieudienNgayMuon, Date bieudienNgayTra, String ghiChu, float tienCoc,
			boolean dangmuon, THUTHU thuThu, BANDOC tBanDoc, SACH sach) {
		super();
		this.maMuonTra = maMuonTra;
		this.ngayMuon = bieudienNgayMuon;
		this.ngayTra = bieudienNgayTra;
		this.ghiChu = ghiChu;
		this.tienCoc = tienCoc;
		this.dangmuon = dangmuon;
		this.thuThu = thuThu;
		this.banDoc = tBanDoc;
		this.sach = sach;
	}

	public THEMUONTRA(Date bieudienNgayMuon, Date bieudienNgayTra, String ghiChu, float tienCoc, boolean dangmuon,
			THUTHU thuThu, BANDOC tBanDoc) {
		super();
		this.ngayMuon = bieudienNgayMuon;
		this.ngayTra = bieudienNgayTra;
		
		this.ghiChu = ghiChu;
		this.tienCoc = tienCoc;
		this.dangmuon = dangmuon;
		this.thuThu = thuThu;
		this.banDoc = tBanDoc;
		this.sach = sach;
	}

	public Integer getMaMuonTra() {
		return maMuonTra;
	}

	public void setMaMuonTra(Integer maMuonTra) {
		this.maMuonTra = maMuonTra;
	}

	public Date getNgayMuon() {
		return ngayMuon;
	}
	
	public String getBieuDienNgayMuon() {
		return ngayMuon.getDate()+"/"+(ngayMuon.getMonth()+1)+"/"+(ngayMuon.getYear()+1900);
	}

	public void setNgayMuon(Date bieudienNgayMuon) {
		this.ngayMuon = bieudienNgayMuon;
	}

	public Date getNgayTra() {
		return ngayTra;
	}
	
	public String getBieuDienNgayTra() {
		return ngayTra.getDate()+"/"+(ngayTra.getMonth()+1)+"/"+(ngayTra.getYear()+1900);
	}

	public void setNgayTra(Date bieudienNgayTra) {
		this.ngayTra = bieudienNgayTra;
	}

	public String getGhiChu() {
		if(ghiChu==null||ghiChu.isBlank()) return "Không";
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public float getTienCoc() {
		return tienCoc;
	}

	public void setTienCoc(float tienCoc) {
		this.tienCoc = tienCoc;
	}

	public boolean isDangMuon() {
		return dangmuon;
	}
	
	public String getTrangThai() {
		String a;
		if(dangmuon) {
			a = "CHƯA TRẢ";
		}else a = "ĐÃ TRẢ";
		
		return a;
	}

	public void setDangMuon(boolean dangmuon) {
		this.dangmuon = dangmuon;
	}

	public THUTHU getThuThu() {
		return thuThu;
	}

	public void setThuThu(THUTHU thuThu) {
		this.thuThu = thuThu;
	}

	public SACH getSach() {
		return sach;
	}

	public void setSach(SACH sach) {
		this.sach = sach;
	}

	public boolean isDangmuon() {
		return dangmuon;
	}

	public void setDangmuon(boolean dangmuon) {
		this.dangmuon = dangmuon;
	}

	public BANDOC getBanDoc() {
		return banDoc;
	}

	public void setBanDoc(BANDOC banDoc) {
		this.banDoc = banDoc;
	}
	
	
}
