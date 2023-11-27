package com.shoplaptop.entity;

public class NhaCungCap {
	private String maNCC;
	private String tenNCC;
	private String nguoiPhuTrach;
	private String soDienThoai;
	private String email;
	private String diaChi;
	
	public NhaCungCap() {
		
	}

	public NhaCungCap(String maNCC, String tenNCC, String nguoiPhuTrach, String soDienThoai, String email,
			String diaChi) {
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.nguoiPhuTrach = nguoiPhuTrach;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.diaChi = diaChi;
	}

	public String getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}

	public String getTenNCC() {
		return tenNCC;
	}

	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}

	public String getNguoiPhuTrach() {
		return nguoiPhuTrach;
	}

	public void setNguoiPhuTrach(String nguoiPhuTrach) {
		this.nguoiPhuTrach = nguoiPhuTrach;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	@Override
	public String toString() {
		return tenNCC + " - " + diaChi;
	}
	
	
}
