package model;

public class THELOAI {
	Integer maTheLoai;
	String tenTheLoai;
	Integer sl;
	
	public THELOAI() {}
	public THELOAI(Integer maTheLoai, String tenTheLoai, Integer sl) {
		super();
		this.maTheLoai = maTheLoai;
		this.tenTheLoai = tenTheLoai;
		this.sl = sl;
	}
	public THELOAI(Integer maTheLoai2, String ten) {
		this.maTheLoai = maTheLoai2;
		this.tenTheLoai = ten;
	}
	public Integer getMaTheLoai() {
		return maTheLoai;
	}
	public void setMaTheLoai(Integer maTheLoai) {
		this.maTheLoai = maTheLoai;
	}
	public String getTenTheLoai() {
		return tenTheLoai;
	}
	public void setTenTheLoai(String tenTheLoai) {
		this.tenTheLoai = tenTheLoai;
	}
	public Integer getSl() {
		return sl;
	}
	public void setSl(Integer sl) {
		this.sl = sl;
	}
	
	
}
