package model;

public class THUTHU {
	private Integer maThuThu;
	private String tenThuThu;
	private String diaChi;
	private String SDT;
	private String userName;
	
	public THUTHU() {}

	public THUTHU(int maThuThu, String tenThuThu, String diaChi, String sDT) {
		super();
		this.maThuThu = maThuThu;
		this.tenThuThu = tenThuThu;
		this.diaChi = diaChi;
		SDT = sDT;
	}

	public Integer getMaThuThu() {
		return maThuThu;
	}

	public void setMaThuThu(int maThuThu) {
		this.maThuThu = maThuThu;
	}

	public String getTenThuThu() {
		return tenThuThu;
	}

	public void setTenThuThu(String tenThuThu) {
		this.tenThuThu = tenThuThu;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setMaThuThu(Integer maThuThu) {
		this.maThuThu = maThuThu;
	}
	
	
}
