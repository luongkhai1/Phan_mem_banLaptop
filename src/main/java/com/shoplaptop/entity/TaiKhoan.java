package com.shoplaptop.entity;

public class TaiKhoan {
	private String maNV;
	private String tenDangNhap;
	private String matKhau;
	private boolean vaiTro;
	
	public TaiKhoan() {
		
	}

	public TaiKhoan(String maNV, String tenDangNhap, String matKhau, boolean vaiTro) {
		this.maNV = maNV;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.vaiTro = vaiTro;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public boolean isVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(boolean vaiTro) {
		this.vaiTro = vaiTro;
	}
	
}
