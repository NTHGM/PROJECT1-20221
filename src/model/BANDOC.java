package model;

import java.sql.Date;
import java.util.ArrayList;

public class BANDOC {
	private Integer maBanDoc;
	private String tenBanDoc;
	private String gioiTinh;
	private String diaChi;
	private Date ngaySinh;
	private String userName;
	Date ngayGiaHan;
	Date ngayHetHan;

	
	public BANDOC() {}

	public BANDOC(String tenBanDoc, String gioiTinh, String diaChi, Date ngaySinh, String userName) {
		super();
		this.tenBanDoc = tenBanDoc;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.ngaySinh = ngaySinh;
		this.userName = userName;
	}

	

	public BANDOC(int maBanDoc, String tenBanDoc, String gioiTinh, String diaChi, Date bieudienNgaySinh,Date ngh,Date nhh) {
		this.maBanDoc = maBanDoc;
		this.tenBanDoc = tenBanDoc;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.ngaySinh = bieudienNgaySinh;
		ngayGiaHan  =ngh;
		ngayHetHan = nhh;
	}

	public Integer getMaBanDoc() {
		return maBanDoc;
	}
	
	public void setMaBanDoc(int maBanDoc) {
		this.maBanDoc = maBanDoc;
	}

	public String getTenBanDoc() {
		return tenBanDoc;
	}

	public void setTenBanDoc(String tenBanDoc) {
		this.tenBanDoc = tenBanDoc;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getDiaChi() {
		if(diaChi==null||diaChi.isBlank()) return "";
		return diaChi;
	}

	public Date getNgayGiaHan() {
		return ngayGiaHan;
	}
	
	public String getBieudienNgayGiaHan() {
		if(ngayGiaHan==null) return "Chưa gia hạn";
		return ngayGiaHan.getDate()+"/"+(ngayGiaHan.getMonth()+1)+"/"+(ngayGiaHan.getYear()+1900);
	}

	public void setNgayGiaHan(Date ngayGiaHan) {
		this.ngayGiaHan = ngayGiaHan;
	}

	public Date getNgayHetHan() {
		return ngayHetHan;
	}
	public String getBieudienNgayHetHan() {
		if(ngayHetHan==null) return "Chưa gia hạn";
		return ngayHetHan.getDate()+"/"+(ngayHetHan.getMonth()+1)+"/"+(ngayHetHan.getYear()+1900);
	}

	public void setNgayHetHan(Date ngayHetHan) {
		this.ngayHetHan = ngayHetHan;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getBieudienNgaySinh() {
		return ngaySinh.getDate()+"/"+(ngaySinh.getMonth()+1)+"/"+(ngaySinh.getYear()+1900);
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setMaBanDoc(Integer maBanDoc) {
		this.maBanDoc = maBanDoc;
	}

	public int getGioiTinhIndex() {
		if(getGioiTinh().trim().equals("Nam")) return 0;
		if(getGioiTinh().trim().equals("Nu")) return 1;
		if(getGioiTinh().trim().equals("Khac")) return 2;
		return -1;
	}
	
}
