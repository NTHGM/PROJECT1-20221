package model;

public class NXB {
	private Integer maNXB;
	private String tenNXB;
	private String diaChi;
	private Integer sl;
	
	public NXB() {}
	public NXB(Integer maNXB, String tenNXB, String diaChi, Integer sl) {
		super();
		this.maNXB = maNXB;
		this.tenNXB = tenNXB;
		this.diaChi = diaChi;
		this.sl=sl;
	}
	
	public NXB(Integer maNXB, String tenNXB, String diaChi) {
		this.maNXB = maNXB;
		this.tenNXB = tenNXB;
		this.diaChi = diaChi;
	}
	public Integer getSl() {
		return sl;
	}
	public void setSl(Integer sl) {
		this.sl = sl;
	}
	public Integer getMaNXB() {
		return maNXB;
	}
	public void setMaNXB(Integer maNXB) {
		this.maNXB = maNXB;
	}
	public String getTenNXB() {
		return tenNXB;
	}
	public void setTenNXB(String tenNXB) {
		this.tenNXB = tenNXB;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	
	
}
