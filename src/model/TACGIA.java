package model;

public class TACGIA {
	private Integer maTacGia;
	private String tenTacGia;
	private Integer sl;
	
	public TACGIA() {}
	public TACGIA(String ten) {
		tenTacGia=ten;
	}
	public TACGIA(Integer id, String ten) {
		maTacGia = id;
		tenTacGia = ten;
	}
	public TACGIA(Integer id, String ten, Integer sl) {
		maTacGia = id;
		tenTacGia=ten;
		this.sl=sl;
	}
	
	public Integer getSl() {
		return sl;
	}

	public void setSl(Integer sl) {
		this.sl = sl;
	}

	public void setMaTacGia(Integer maTacGia) {
		this.maTacGia = maTacGia;
	}
	
	public Integer getMaTacGia() {
		return maTacGia;
	}

	

	public String getTenTacGia() {
		return tenTacGia;
	}

	public void setTenTacGia(String tenTacGia) {
		this.tenTacGia = tenTacGia;
	}
	
	
}
